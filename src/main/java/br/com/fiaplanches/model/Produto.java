package br.com.fiaplanches.model;

import br.com.fiaplanches.enuns.Categoria;
import br.com.fiaplanches.records.CadastrarProduto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.math.BigDecimal;
import java.util.Objects;

@Entity(name = "Produto")
@Table(name = "produto")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeProduto;
    private BigDecimal preco;
    @Enumerated(EnumType.STRING)
    private Categoria categoria;



    public Produto(CadastrarProduto cadastrarProduto) {
        this.nomeProduto = cadastrarProduto.nomeProduto();
        this.preco = cadastrarProduto.preco();
        this.categoria = cadastrarProduto.categoria();
    }

    public void atualizaProduto(Produto atualizarProduto) {
        if (atualizarProduto.getNomeProduto() != null) {
            this.nomeProduto = atualizarProduto.getNomeProduto();
        }
        if (atualizarProduto.getCategoria() != null) {
            this.categoria = atualizarProduto.getCategoria();
        }
        if (atualizarProduto.getPreco() != null) {
            this.preco = atualizarProduto.getPreco();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Produto produto = (Produto) o;
        return getId() != null && Objects.equals(getId(), produto.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
