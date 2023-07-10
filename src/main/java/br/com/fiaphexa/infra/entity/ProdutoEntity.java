package br.com.fiaphexa.infra.entity;

import br.com.fiaphexa.dominio.dtos.produto.ProdutoDto;
import br.com.fiaphexa.dominio.enuns.Categoria;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    public ProdutoEntity(ProdutoDto produtoDTO) {
        this.id = produtoDTO.id();
        this.nomeProduto = produtoDTO.nomeProduto();
        this.preco = produtoDTO.preco();
        this.categoria = produtoDTO.categoria();
    }

    public ProdutoDto toProdutoDto() {
        return new ProdutoDto(
                this.getId(),
                this.getNomeProduto(),
                this.getPreco(),
                this.getCategoria()
        );
    }
}
