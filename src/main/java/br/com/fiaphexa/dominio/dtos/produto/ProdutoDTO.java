package br.com.fiaphexa.dominio.dtos.produto;

import br.com.fiaphexa.dominio.enuns.Categoria;

import java.math.BigDecimal;

public record ProdutoDTO(
        Long id,
        String nomeProduto,
        BigDecimal preco,
        Categoria categoria
) {
}
