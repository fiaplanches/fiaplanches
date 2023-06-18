package br.com.fiaplanches.model;

import br.com.fiaplanches.enuns.StatusPedido;
import br.com.fiaplanches.records.PedidoRecord;
import br.com.fiaplanches.records.RetornaProduto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "pedido")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToMany
    @JoinTable(name = "pedido_produto",
            joinColumns = @JoinColumn(name = "pedido_id"),
            inverseJoinColumns = @JoinColumn(name = "produto_id"))
    private List<Produto> produtos;

    @Column(name = "data_pedido")
    LocalDateTime dataPedido;

    @Enumerated(EnumType.STRING)
    private StatusPedido statusPedido;

    public Pedido(Cliente cliente, List<Produto> listaProdutos) {
        this.produtos = listaProdutos;
        this.cliente = cliente;
        this.dataPedido = LocalDateTime.now();
        this.statusPedido = StatusPedido.RECEBIDO;
    }

    public List<RetornaProduto> getListaProdutosRecord() {
        return this.produtos.stream().map(RetornaProduto::new).toList();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Pedido pedido = (Pedido) o;
        return getId() != null && Objects.equals(getId(), pedido.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
