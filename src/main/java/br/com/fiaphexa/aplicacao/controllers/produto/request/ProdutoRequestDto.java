package br.com.fiaphexa.aplicacao.controllers.produto.request;

import br.com.fiaphexa.dominio.dtos.produto.ProdutoDto;
import br.com.fiaphexa.dominio.enuns.Categoria;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

public record ProdutoRequestDto(
        @NotBlank
        String nomeProduto,
        @NotBlank
        BigDecimal preco,
        @NotBlank
        Categoria categoria
) {
    public ProdutoDto toProduto() {
        return new ProdutoDto(
                null,
                this.nomeProduto,
                this.preco,
                this.categoria
        );
    }
}
