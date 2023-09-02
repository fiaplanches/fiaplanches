package br.com.fiaphexa.web.controllers.pedido.response;

import br.com.fiaphexa.aplicacao.dtos.pedido.PedidoDto;

import java.util.List;

public record BuscaPedidoResponse(List<PedidoDto> pedidos) {
}
