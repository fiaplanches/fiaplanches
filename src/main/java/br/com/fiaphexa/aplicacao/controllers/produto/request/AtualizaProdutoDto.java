package br.com.fiaphexa.aplicacao.controllers.produto.request;

import br.com.fiaphexa.dominio.dtos.produto.ProdutoDto;
import br.com.fiaphexa.dominio.enuns.Categoria;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record AtualizaProdutoDto(
        Long id,
        @NotBlank(message = "Nome do produto não pode ser vazio")
        String nomeProduto,
        @NotNull(message = "Preço não pode ser vazio")
        @DecimalMin(value = "0.0", inclusive = false, message = "Preço deve ser maior que 0")
        BigDecimal preco,
        @NotNull(message = "Categoria nao pode ser vazia")
        @Enumerated(EnumType.STRING)
        Categoria categoria
) {

    public ProdutoDto toProdutoDto() {
        return new ProdutoDto(
                this.id,
                this.nomeProduto,
                this.preco,
                this.categoria
        );
    }
}
