package br.com.fiaphexa.dominio.dtos.produto;

import br.com.fiaphexa.dominio.enuns.Categoria;
import br.com.fiaphexa.dominio.model.Produto;

import java.math.BigDecimal;

public record ProdutoDto(
        Long id,
        String nomeProduto,
        BigDecimal preco,
        Categoria categoria
) {
    public ProdutoDto(Long id, String nomeProduto, BigDecimal preco, Categoria categoria) {
        this.id = id;
        this.nomeProduto = nomeProduto;
        this.preco = preco;
        this.categoria = categoria;
    }

    public static ProdutoDto toProdutoDto(Produto produto) {
        return new ProdutoDto(
                produto.getId(),
                produto.getNomeProduto(),
                produto.getPreco(),
                produto.getCategoria()
        );
    }

    public Produto toProduto() {
        return new Produto(
                this.id,
                this.nomeProduto,
                this.preco,
                this.categoria
        );
    }
}
