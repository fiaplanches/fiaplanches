package br.com.fiaphexa.dominio.portas.entrada.pedidos;

import br.com.fiaphexa.dominio.dtos.pedido.PedidoDto;

public interface CriaPedidoPortaEntrada {

    public PedidoDto criaPedido(PedidoDto pedidoDto);
}
