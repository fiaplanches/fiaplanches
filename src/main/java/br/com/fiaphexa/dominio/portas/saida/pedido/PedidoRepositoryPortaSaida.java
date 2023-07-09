package br.com.fiaphexa.dominio.portas.saida.pedido;

import br.com.fiaphexa.dominio.dtos.PageInfoDto;
import br.com.fiaphexa.dominio.dtos.pedido.PedidoDto;

import java.util.List;

public interface PedidoRepositoryPortaSaida {
    PedidoDto criaPedido(PedidoDto pedidoDto);
    List<PedidoDto> buscaPedidosCliente(String cpf, PageInfoDto page);
    List<PedidoDto> buscaPedidos(PageInfoDto page);
}
