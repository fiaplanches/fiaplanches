package br.com.fiaplanches.model;

import br.com.fiaplanches.records.CriarCliente;
import br.com.fiaplanches.records.RetornoCliente;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity(name = "Cliente")
@Table(name = "cliente")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long cpf;
    private String nome;

    public Cliente(CriarCliente cliente) {
        this.cpf = cliente.cpf();
        this.nome = cliente.nome();
    }

    public Cliente(){
    }

    public Long getCpf() {
        return cpf;
    }

    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void atualizaCliente(RetornoCliente clienteUpdate) {
        if (clienteUpdate.nome() != null) {
            this.nome = clienteUpdate.nome();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
