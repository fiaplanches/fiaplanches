package br.com.fiaplanches.records;

import br.com.fiaplanches.enuns.StatusPedido;
import br.com.fiaplanches.model.Pedido;

import java.time.LocalDateTime;
import java.util.List;

public record PedidoRecord(Long cpf, List<RetornaProduto> produtos, StatusPedido statusPedido, Long idPedido, LocalDateTime dataPedido) {
    public PedidoRecord(Pedido pedido) {
        this(pedido.getCliente().getCpf(), pedido.getListaProdutosRecord(), pedido.getStatusPedido(), pedido.getId(), pedido.getDataPedido());
    }
}
