package br.com.fiaphexa.aplicacao.controllers.dtos;

import br.com.fiaphexa.dominio.model.Produto;
import br.com.fiaphexa.dominio.enuns.Categoria;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

public record AtualizaProdutoDto(Long id, String nomeProduto, BigDecimal preco, Categoria categoria) {

    public AtualizaProdutoDto(Produto produto){
        this(produto.getId(), produto.getNomeProduto(), produto.getPreco(), produto.getCategoria());
    }

    public Produto toProduto() {
        var produto = new Produto();
        BeanUtils.copyProperties(this, produto);
        return produto;
    }
}
