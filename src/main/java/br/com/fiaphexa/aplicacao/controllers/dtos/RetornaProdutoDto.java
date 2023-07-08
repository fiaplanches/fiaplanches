package br.com.fiaphexa.aplicacao.controllers.dtos;

import br.com.fiaphexa.dominio.enuns.Categoria;
import br.com.fiaphexa.dominio.model.Produto;
import br.com.fiaphexa.infra.entity.ProdutoEntity;

import java.math.BigDecimal;

public record RetornaProdutoDto(Long id, String nomeProduto, BigDecimal preco, Categoria categoria) {

    public RetornaProdutoDto(Produto produto) {
        this(produto.getId(), produto.getNomeProduto(), produto.getPreco(), produto.getCategoria());
    }

}