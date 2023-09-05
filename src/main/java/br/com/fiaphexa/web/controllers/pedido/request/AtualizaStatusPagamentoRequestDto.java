package br.com.fiaphexa.web.controllers.pedido.request;

import br.com.fiaphexa.dominio.enuns.StatusPagamentoEnum;

public record AtualizaStatusPagamentoRequestDto(StatusPagamentoEnum status, String message, String orderId) {
}
