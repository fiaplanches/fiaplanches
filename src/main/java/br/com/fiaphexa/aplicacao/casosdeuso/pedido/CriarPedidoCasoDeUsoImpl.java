package br.com.fiaphexa.aplicacao.casosdeuso.pedido;

import br.com.fiaphexa.aplicacao.excecoes.erros.PaymentRefusedException;
import br.com.fiaphexa.aplicacao.dtos.cliente.ClienteDto;
import br.com.fiaphexa.aplicacao.dtos.pedido.PedidoComIdProdutosDto;
import br.com.fiaphexa.aplicacao.dtos.pedido.PedidoDto;
import br.com.fiaphexa.aplicacao.dtos.produto.ProdutoDto;
import br.com.fiaphexa.dominio.enuns.StatusPedido;
import br.com.fiaphexa.dominio.model.Pedido;
import br.com.fiaphexa.dominio.model.Produto;
import br.com.fiaphexa.aplicacao.casosdeuso.abstracoes.pedidos.CriaPedidoCasoDeUso;
import br.com.fiaphexa.aplicacao.repositorios.cliente.ClienteRepositoryService;
import br.com.fiaphexa.aplicacao.repositorios.pedido.PedidoPagamentoRepositoryService;
import br.com.fiaphexa.aplicacao.repositorios.pedido.PedidoRepositoryService;
import br.com.fiaphexa.aplicacao.repositorios.produto.ProdutoRepositoryService;
import jakarta.persistence.EntityNotFoundException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class CriarPedidoCasoDeUsoImpl implements CriaPedidoCasoDeUso {

    private final PedidoRepositoryService pedidoRepositoryService;

    private final ClienteRepositoryService clienteRepositoryService;

    private final ProdutoRepositoryService produtoRepositoryService;

    private final PedidoPagamentoRepositoryService pedidoPagamentoService;

    public CriarPedidoCasoDeUsoImpl(PedidoRepositoryService pedidoRepositoryService, ClienteRepositoryService clienteRepositoryService, ProdutoRepositoryService produtoRepositoryService, PedidoPagamentoRepositoryService pedidoPagamentoService) {
        this.pedidoRepositoryService = pedidoRepositoryService;
        this.clienteRepositoryService = clienteRepositoryService;
        this.produtoRepositoryService = produtoRepositoryService;
        this.pedidoPagamentoService = pedidoPagamentoService;
    }

    @Override
    public PedidoDto criaPedido(PedidoComIdProdutosDto pedidoComIdProdutosDto) {
        var clienteDto = clienteRepositoryService.procuraClientePorCpf(pedidoComIdProdutosDto.cpf())
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));
        List<Long> idProdutos = pedidoComIdProdutosDto.produtos();
        var listaProdutos = produtoRepositoryService.procuraProdutosPorId(idProdutos);
        var pedido = new Pedido(
                null,
                clienteDto.toCliente(),
                listaProdutos.stream().map(ProdutoDto::toProduto).toList(),
                LocalDateTime.now(),
                StatusPedido.RECEBIDO,
                Boolean.FALSE
        );
        return pagamentoPedido(pedido);
    }

    private PedidoDto pagamentoPedido(Pedido pedido) {

        var valorTotal = calculaValorTotal(pedido.getProdutos());
        var checkoutPedido = pedidoPagamentoService.pagamentoPedido(pedido.getCliente().getCpf(), valorTotal);

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
        var pedidoDto = new PedidoDto(pedido.getId(), clienteDto, produtoDtos, pedido.getDataPedido(), pedido.getStatusPedido(), pedido.getApproved());
        return pedidoRepositoryService.salvaPedido(pedidoDto);
    }

    private BigDecimal calculaValorTotal(List<Produto> produtos) {
        return produtos.stream().map(Produto::getPreco).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
