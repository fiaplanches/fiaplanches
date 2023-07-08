package br.com.fiaphexa.dominio.model;

import br.com.fiaphexa.dominio.enuns.StatusPedido;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class Pedido {

    private Long id;

    private Cliente cliente;

    private List<Produto> produtos;

    LocalDateTime dataPedido;

    private StatusPedido statusPedido;

    public Pedido(Cliente cliente, List<Produto> listaProdutos) {
        this.produtos = listaProdutos;
        this.cliente = cliente;
        this.dataPedido = LocalDateTime.now();
        this.statusPedido = StatusPedido.RECEBIDO;
    }

    public Pedido() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public LocalDateTime getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(LocalDateTime dataPedido) {
        this.dataPedido = dataPedido;
    }

    public StatusPedido getStatusPedido() {
        return statusPedido;
    }

    public void setStatusPedido(StatusPedido statusPedido) {
        this.statusPedido = statusPedido;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pedido pedido = (Pedido) o;
        return Objects.equals(id, pedido.id) && Objects.equals(cliente, pedido.cliente) && Objects.equals(produtos, pedido.produtos) && Objects.equals(dataPedido, pedido.dataPedido) && statusPedido == pedido.statusPedido;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cliente, produtos, dataPedido, statusPedido);
    }
}