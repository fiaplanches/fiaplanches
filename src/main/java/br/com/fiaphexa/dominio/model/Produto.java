package br.com.fiaphexa.dominio.model;

import br.com.fiaphexa.dominio.enuns.Categoria;

import java.math.BigDecimal;
import java.util.Objects;

public class Produto {

    private Long id;
    private String nomeProduto;
    private BigDecimal preco;
    private Categoria categoria;

    public Produto() {
    }

    public Produto(Long id, String nomeProduto, BigDecimal preco, Categoria categoria) {
        this.id = id;
        this.nomeProduto = nomeProduto;
        this.preco = preco;
        this.categoria = categoria;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return Objects.equals(id, produto.id) && Objects.equals(nomeProduto, produto.nomeProduto) && Objects.equals(preco, produto.preco) && categoria == produto.categoria;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nomeProduto, preco, categoria);
    }

    public Produto atualizaProduto(Produto novoProduto) {
        if (!novoProduto.getNomeProduto().isEmpty() & novoProduto.getNomeProduto() != null) {
            setNomeProduto(novoProduto.getNomeProduto());
        }
        if (novoProduto.getCategoria() != null) {
            setCategoria(novoProduto.getCategoria());
        }
        if (novoProduto.getPreco() != null) {
            setPreco(novoProduto.getPreco());
        }

        return this;
    }
}