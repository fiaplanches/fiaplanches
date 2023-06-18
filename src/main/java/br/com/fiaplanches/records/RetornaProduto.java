package br.com.fiaplanches.records;

import br.com.fiaplanches.enuns.Categoria;
import br.com.fiaplanches.model.Produto;

import java.math.BigDecimal;

public record RetornaProduto(Long id, String nomeProduto, BigDecimal preco, Categoria categoria) {

    public RetornaProduto(Produto produto) {
        this(produto.getId(), produto.getNomeProduto(), produto.getPreco(), produto.getCategoria());
    }

}
