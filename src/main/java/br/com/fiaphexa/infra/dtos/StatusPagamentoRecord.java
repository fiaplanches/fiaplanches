package br.com.fiaphexa.infra.dtos;

import br.com.fiaphexa.dominio.enuns.StatusPagamentoEnum;

public record StatusPagamentoRecord(StatusPagamentoEnum status, String message, String orderId)  {
}
