package br.com.fiaphexa.dominio.portas.entrada.pedidos;

import br.com.fiaphexa.dominio.dtos.PageInfoDto;
import br.com.fiaphexa.dominio.dtos.pedido.PedidoDto;

import java.util.List;

public interface BuscaPedidosPortaEntrada {

    List<PedidoDto> buscaPedidos(PageInfoDto page);

}
