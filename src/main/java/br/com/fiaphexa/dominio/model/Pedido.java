package br.com.fiaphexa.dominio.model;

import br.com.fiaphexa.dominio.enuns.StatusPedido;

import javax.swing.text.StyledEditorKit;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class Pedido {

    private Long id;

    private Cliente cliente;

    private List<Produto> produtos;

    LocalDateTime dataPedido;

    private StatusPedido statusPedido;

    private Boolean isApproved = false;

    public Pedido() {
    }

    public Pedido(Long id, Cliente cliente, List<Produto> produtos, LocalDateTime dataPedido, StatusPedido statusPedido, Boolean isApproved) {
        this.id = id;
        this.cliente = cliente;
        this.produtos = produtos;
        this.dataPedido = dataPedido;
        this.statusPedido = statusPedido;
        this.isApproved = isApproved;
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

    public Boolean getApproved() {
        return isApproved;
    }

    public void setApproved(Boolean approved) {
        isApproved = approved;
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