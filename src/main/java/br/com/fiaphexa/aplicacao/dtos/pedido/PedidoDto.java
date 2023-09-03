package br.com.fiaphexa.aplicacao.dtos.pedido;

import br.com.fiaphexa.aplicacao.dtos.cliente.ClienteDto;
import br.com.fiaphexa.aplicacao.dtos.produto.ProdutoDto;
import br.com.fiaphexa.dominio.enuns.StatusPedido;
import br.com.fiaphexa.dominio.model.Pedido;

import java.time.LocalDateTime;
import java.util.List;

public record PedidoDto(
        Long id,
        ClienteDto clienteDto,
        List<ProdutoDto> produtosDtos,
        LocalDateTime dataPedido,
        StatusPedido statusPedido,
        Boolean isApproved
) {

    public PedidoDto(Long id, ClienteDto clienteDto, List<ProdutoDto> produtosDtos, LocalDateTime dataPedido, StatusPedido statusPedido, Boolean isApproved) {
        this.id = id;
        this.clienteDto = clienteDto;
        this.produtosDtos = produtosDtos;
        this.dataPedido = dataPedido;
        this.statusPedido = statusPedido;
        this.isApproved = isApproved;
    }

    public static PedidoDto toPedidoDto(Pedido pedido) {
        return new PedidoDto(
                pedido.getId(),
                ClienteDto.toClienteDto(pedido.getCliente()),
                pedido.getProdutos().stream().map(ProdutoDto::toProdutoDto).toList(),
                pedido.getDataPedido(),
                pedido.getStatusPedido(),
                pedido.getApproved()
        );
    }

    public Pedido toPedido() {
        return new Pedido(
                this.id,
                this.clienteDto.toCliente(),
                this.produtosDtos.stream().map(ProdutoDto::toProduto).toList(),
                this.dataPedido,
                this.statusPedido,
                this.isApproved
        );
    }

    public String statusToString(){
        return isApproved ? "Aprovado" : "Não Aprovado";
    }
}
