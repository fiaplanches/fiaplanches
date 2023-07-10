package br.com.fiaphexa.dominio.services.pedido;

import br.com.fiaphexa.aplicacao.excecoes.erros.PaymentRefusedException;
import br.com.fiaphexa.dominio.dtos.cliente.ClienteDto;
import br.com.fiaphexa.dominio.dtos.pedido.PedidoComIdProdutosDto;
import br.com.fiaphexa.dominio.dtos.pedido.PedidoDto;
import br.com.fiaphexa.dominio.dtos.produto.ProdutoDto;
import br.com.fiaphexa.dominio.enuns.StatusPedido;
import br.com.fiaphexa.dominio.model.Pedido;
import br.com.fiaphexa.dominio.model.Produto;
import br.com.fiaphexa.dominio.portas.entrada.pedidos.CriaPedidoPortaEntrada;
import br.com.fiaphexa.dominio.portas.saida.cliente.ClienteRepositoryPortaSaida;
import br.com.fiaphexa.dominio.portas.saida.pedido.PedidoPagamentoPortaSaida;
import br.com.fiaphexa.dominio.portas.saida.pedido.PedidoRepositoryPortaSaida;
import br.com.fiaphexa.dominio.portas.saida.produto.ProdutoRepositoryPortaSaida;
import jakarta.persistence.EntityNotFoundException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class CriarPedidosService implements CriaPedidoPortaEntrada {

    private final PedidoRepositoryPortaSaida pedidoRepositoryPortaSaida;

    private final ClienteRepositoryPortaSaida clienteRepositoryPortaSaida;

    private final ProdutoRepositoryPortaSaida produtoRepositoryPortaSaida;

    private final PedidoPagamentoPortaSaida pedidoPagamentoPortaSaida;

    public CriarPedidosService(PedidoRepositoryPortaSaida pedidoRepositoryPortaSaida, ClienteRepositoryPortaSaida clienteRepositoryPortaSaida, ProdutoRepositoryPortaSaida produtoRepositoryPortaSaida, PedidoPagamentoPortaSaida pedidoPagamentoPortaSaida) {
        this.pedidoRepositoryPortaSaida = pedidoRepositoryPortaSaida;
        this.clienteRepositoryPortaSaida = clienteRepositoryPortaSaida;
        this.produtoRepositoryPortaSaida = produtoRepositoryPortaSaida;
        this.pedidoPagamentoPortaSaida = pedidoPagamentoPortaSaida;
    }

    @Override
    public PedidoDto criaPedido(PedidoComIdProdutosDto pedidoComIdProdutosDto) {
        var clienteDto = clienteRepositoryPortaSaida.procuraClientePorCpf(pedidoComIdProdutosDto.cpf())
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));
        List<Long> idProdutos = pedidoComIdProdutosDto.produtos();
        var listaProdutos = produtoRepositoryPortaSaida.procuraProdutosPorId(idProdutos);
        var pedido = new Pedido(
                null,
                clienteDto.toCliente(),
                listaProdutos.stream().map(ProdutoDto::toProduto).toList(),
                LocalDateTime.now(),
                StatusPedido.RECEBIDO
        );
        return pagamentoPedido(pedido);
    }

    private PedidoDto pagamentoPedido(Pedido pedido) {

        var valorTotal = calculaValorTotal(pedido.getProdutos());
        var checkoutPedido = pedidoPagamentoPortaSaida.pagamentoPedido(pedido.getCliente().getCpf(), valorTotal);

        if(!checkoutPedido.status()){
            throw new PaymentRefusedException(checkoutPedido.message());
        }
        var clienteDto = new ClienteDto(pedido.getCliente().getId(), pedido.getCliente().getCpf(), pedido.getCliente().getNome());
        var produtoDtos = pedido.getProdutos()
                .stream()
                .map(produto -> new ProdutoDto(
                        produto.getId(),
                        produto.getNomeProduto(),
                        produto.getPreco(),
                        produto.getCategoria()
                ))
                .toList();
        var pedidoDto = new PedidoDto(pedido.getId(), clienteDto, produtoDtos, pedido.getDataPedido(), pedido.getStatusPedido());
        return pedidoRepositoryPortaSaida.salvaPedido(pedidoDto);
    }

    private BigDecimal calculaValorTotal(List<Produto> produtos) {
        return produtos.stream().map(Produto::getPreco).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
