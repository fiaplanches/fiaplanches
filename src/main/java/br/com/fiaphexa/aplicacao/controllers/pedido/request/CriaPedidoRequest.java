package br.com.fiaphexa.aplicacao.controllers.pedido.request;

import java.util.List;

public record CriaPedidoRequest(String cpf, List<Long> produtos) {
}
