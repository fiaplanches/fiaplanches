package br.com.fiaphexa.dominio.portas.entrada.pedidos;

import br.com.fiaphexa.dominio.dtos.PageInfoDto;
import br.com.fiaphexa.dominio.dtos.pedido.PedidoDto;

import java.util.List;

public interface BuscaPedidosClientePortaEntrada {

    public List<PedidoDto> buscaPedidosCliente(String cpf, PageInfoDto page);
}
