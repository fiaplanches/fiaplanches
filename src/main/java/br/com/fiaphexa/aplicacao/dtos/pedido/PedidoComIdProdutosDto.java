package br.com.fiaphexa.aplicacao.dtos.pedido;

import java.util.List;

public record PedidoComIdProdutosDto(String cpf, List<Long> produtos) {


}
