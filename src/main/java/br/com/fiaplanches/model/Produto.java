package br.com.fiaplanches.model;

import br.com.fiaplanches.enuns.Categoria;
import br.com.fiaplanches.records.CadastrarProduto;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity(name = "Produto")
@Table(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeProduto;
    private BigDecimal preco;

    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    public Produto() {
    }

    public Produto(CadastrarProduto cadastrarProduto) {
        this.nomeProduto = cadastrarProduto.nomeProduto();
        this.preco = cadastrarProduto.preco();
        this.categoria = cadastrarProduto.categoria();
    }

    public void atualizaProduto(Produto atualizarProduto) {
        if (atualizarProduto.getNomeProduto() != null) {
            System.out.println(atualizarProduto.getNomeProduto());
            this.nomeProduto = atualizarProduto.getNomeProduto();
        }
        if (atualizarProduto.getCategoria() != null) {
            System.out.println(atualizarProduto.getCategoria());
            this.categoria = atualizarProduto.getCategoria();
        }
        if (atualizarProduto.getPreco() != null) {
            System.out.println(atualizarProduto.getPreco());
            this.preco = atualizarProduto.getPreco();
        }
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
}
