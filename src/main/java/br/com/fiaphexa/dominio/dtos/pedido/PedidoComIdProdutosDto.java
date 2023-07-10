package br.com.fiaphexa.dominio.dtos.pedido;

import java.util.List;

public record PedidoComIdProdutosDto(String cpf, List<Long> produtos) {


}
