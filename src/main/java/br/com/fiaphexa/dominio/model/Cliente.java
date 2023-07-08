package br.com.fiaphexa.dominio.model;

import java.util.List;
import java.util.Objects;

public class Cliente {

    private Long id;

    private Long cpf;

    private String nome;

    private List<Pedido> pedidos;

    public Cliente(Long id, Long cpf, String nome, List<Pedido> pedidos) {
        this.id = id;
        this.cpf = cpf;
        this.nome = nome;
        this.pedidos = pedidos;
    }

    public Cliente() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(id, cliente.id) && Objects.equals(cpf, cliente.cpf) && Objects.equals(nome, cliente.nome) && Objects.equals(pedidos, cliente.pedidos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cpf, nome, pedidos);
    }
}
