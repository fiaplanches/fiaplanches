package br.com.fiaphexa.aplicacao.casosdeuso.pedido;

import br.com.fiaphexa.aplicacao.casosdeuso.abstracoes.pedidos.AdicionaNoCarrinhoCasoDeUso;
import br.com.fiaphexa.aplicacao.dtos.pedido.PedidoComIdProdutosDto;
import br.com.fiaphexa.aplicacao.dtos.pedido.PedidoDto;
import br.com.fiaphexa.aplicacao.dtos.produto.ProdutoDto;
import br.com.fiaphexa.aplicacao.repositorios.cliente.ClienteRepositoryService;
import br.com.fiaphexa.aplicacao.repositorios.pedido.PedidoRepositoryService;
import br.com.fiaphexa.aplicacao.repositorios.produto.ProdutoRepositoryService;
import br.com.fiaphexa.dominio.enuns.StatusPedido;
import br.com.fiaphexa.dominio.model.Pedido;
import jakarta.persistence.EntityNotFoundException;

import java.time.LocalDateTime;
import java.util.List;

public class AdicionaNoCarrinhoCasoDeUsoImpl implements AdicionaNoCarrinhoCasoDeUso {

    private final PedidoRepositoryService pedidoRepositoryService;

    private final ClienteRepositoryService clienteRepositoryService;

    private final ProdutoRepositoryService produtoRepositoryService;

    public AdicionaNoCarrinhoCasoDeUsoImpl(PedidoRepositoryService pedidoRepositoryService,
                                           ClienteRepositoryService clienteRepositoryService,
                                           ProdutoRepositoryService produtoRepositoryService) {
        this.pedidoRepositoryService = pedidoRepositoryService;
        this.clienteRepositoryService = clienteRepositoryService;
        this.produtoRepositoryService = produtoRepositoryService;
    }

    @Override
    public PedidoDto adicionaNoCarrinho(PedidoComIdProdutosDto pedidoComIdProdutosDto) {

        var clienteDto = clienteRepositoryService.procuraClientePorCpf(pedidoComIdProdutosDto.cpf())
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));
        List<Long> idProdutos = pedidoComIdProdutosDto.produtos();
        var listaProdutos = produtoRepositoryService.procuraProdutosPorId(idProdutos);
        var pedido = new Pedido(
                null,
                clienteDto.toCliente(),
                listaProdutos.stream().map(ProdutoDto::toProduto).toList(),
                LocalDateTime.now(),
                StatusPedido.NO_CARRINHO,
                Boolean.FALSE
        );
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
}
