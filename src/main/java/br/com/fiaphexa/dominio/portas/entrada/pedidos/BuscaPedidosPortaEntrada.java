package br.com.fiaphexa.dominio.portas.entrada.pedidos;

import br.com.fiaphexa.dominio.dtos.pedido.PedidoDto;

import java.awt.print.Pageable;
import java.util.List;

public interface BuscaPedidosPortaEntrada {

    List<PedidoDto> buscaPedidos(Pageable page);

}
