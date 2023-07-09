package br.com.fiaphexa.aplicacao.controllers.pedido.response;

import br.com.fiaphexa.dominio.dtos.pedido.PedidoDto;

import java.util.List;

public record BuscaPedidoResponse(List<PedidoDto> pedidos) {
}
