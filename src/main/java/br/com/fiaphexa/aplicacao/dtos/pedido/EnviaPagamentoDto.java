package br.com.fiaphexa.aplicacao.dtos.pedido;

import br.com.fiaphexa.dominio.enuns.MetodoPagamentoEnum;

public record EnviaPagamentoDto(MetodoPagamentoEnum paymentMethod, String orderId) {
}
