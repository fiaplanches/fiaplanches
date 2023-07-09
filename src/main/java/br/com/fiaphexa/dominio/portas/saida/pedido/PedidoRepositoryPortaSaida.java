package br.com.fiaphexa.dominio.portas.saida.pedido;

import br.com.fiaphexa.dominio.dtos.pedido.PedidoDto;
import org.springframework.data.domain.Page;

public interface PedidoRepositoryPortaSaida
{
PedidoDto criaPedido(PedidoDto pedidoDto);
Page<PedidoDto> buscaPedidos(String cpf);

}
