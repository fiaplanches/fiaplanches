package br.com.fiaphexa.aplicacao.controllers.produto.response;

import br.com.fiaphexa.dominio.dtos.produto.ProdutoDto;
import br.com.fiaphexa.dominio.enuns.Categoria;

import java.math.BigDecimal;

public record RetornaProdutoDto(Long id, String nomeProduto, BigDecimal preco, Categoria categoria) {

    public RetornaProdutoDto(ProdutoDto produtoDTO) {
        this(produtoDTO.id(), produtoDTO.nomeProduto(), produtoDTO.preco(), produtoDTO.categoria());
    }

}