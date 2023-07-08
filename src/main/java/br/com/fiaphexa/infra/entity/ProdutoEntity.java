package br.com.fiaphexa.infra.entity;

import br.com.fiaphexa.dominio.model.Produto;
import br.com.fiaphexa.dominio.enuns.Categoria;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

@Entity
@Table(name = "produto")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeProduto;
    private BigDecimal preco;
    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    public ProdutoEntity(Produto produto) {
        this.id = produto.getId();
        this.nomeProduto = produto.getNomeProduto();
        this.preco = produto.getPreco();
        this.categoria = produto.getCategoria();
    }

    public Produto toProduto() {
        var produto = new Produto();
        BeanUtils.copyProperties(this, produto);
        return produto;
    }
}
