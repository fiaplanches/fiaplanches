package br.com.fiaphexa.web.controllers.produto.request;

import br.com.fiaphexa.aplicacao.dtos.produto.ProdutoDto;
import br.com.fiaphexa.dominio.enuns.Categoria;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProdutoRequestDto(
        @NotBlank(message = "Nome do produto não pode ser vazio")
        String nomeProduto,
        @NotNull(message = "Preço não pode ser vazio")
        @DecimalMin(value = "0.0", inclusive = false, message = "Preço deve ser maior que 0")
        BigDecimal preco,
        @NotNull(message = "Categoria nao pode ser vazio")
        @Enumerated(EnumType.STRING)
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
