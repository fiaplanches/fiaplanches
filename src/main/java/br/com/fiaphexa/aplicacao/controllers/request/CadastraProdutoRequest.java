package br.com.fiaphexa.aplicacao.controllers.request;

import br.com.fiaphexa.dominio.enuns.Categoria;
import br.com.fiaphexa.dominio.model.Produto;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

public record CadastraProdutoRequest(
        @NotBlank
        String nomeProduto,
        @NotBlank
        BigDecimal preco,
        @NotBlank
        Categoria categoria
) {
    public Produto toProduto() {
        var produto = new Produto();
        BeanUtils.copyProperties(this, produto, "id");
        return produto;
    }
}
