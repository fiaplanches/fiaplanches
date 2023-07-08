package br.com.fiaphexa.aplicacao.dtos.produto;

import br.com.fiaphexa.dominio.dtos.produto.ProdutoDto;
import br.com.fiaphexa.dominio.enuns.Categoria;

import java.math.BigDecimal;

public record AtualizaProdutoDto(Long id, String nomeProduto, BigDecimal preco, Categoria categoria) {

    public ProdutoDto toProduto() {
        return new ProdutoDto(
                this.id,
                this.nomeProduto,
                this.preco,
                this.categoria
        );
    }
}
