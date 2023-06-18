package br.com.fiaplanches.records;

import br.com.fiaplanches.enuns.Categoria;
import br.com.fiaplanches.model.Produto;

import java.math.BigDecimal;

public record CadastrarProduto(String nomeProduto, BigDecimal preco, Categoria categoria) {

    public CadastrarProduto(Produto produto) {
        this(produto.getNomeProduto(), produto.getPreco(), produto.getCategoria());
    }
}
