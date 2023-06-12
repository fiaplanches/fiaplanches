package br.com.fiaplanches.records;

import br.com.fiaplanches.enuns.Categoria;

import java.math.BigDecimal;

public record CadastrarProduto(String nomeProduto, BigDecimal preco, Categoria categoria) {
}
