package br.com.fiaplanches.records;

import java.util.List;

public record CriarPedidoRecord(Long cpf, List<Long> produtos) {
}
