package br.com.fiaplanches.model;

import br.com.fiaplanches.records.CriarCliente;
import br.com.fiaplanches.records.RetornoCliente;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.util.List;
import java.util.Objects;

@Entity(name = "Cliente")
@Table(name = "cliente")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long cpf;

    private String nome;

    @OneToMany
    @JoinColumn(name = "cliente_id")
    @JsonIgnore
    private List<Pedido> pedidos;

    public Cliente(CriarCliente cliente) {
        this.cpf = cliente.cpf();
        this.nome = cliente.nome();
    }

    public void atualizaCliente(RetornoCliente clienteUpdate) {
        if (clienteUpdate.nome() != null) {
            this.nome = clienteUpdate.nome();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Cliente cliente = (Cliente) o;
        return getCpf() != 0 && Objects.equals(getId(), cliente.getCpf());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "cpf = " + cpf + ", " +
                "nome = " + nome + ")";
    }
}
