package br.com.fiaphexa.dominio.portas.entrada.pedidos;

import br.com.fiaphexa.dominio.dtos.pedido.PedidoComIdProdutosDto;
import br.com.fiaphexa.dominio.dtos.pedido.PedidoDto;

public interface CriaPedidoPortaEntrada {

    public PedidoDto criaPedido(PedidoComIdProdutosDto pedidoComIdProdutosDto);
}
