package br.com.fiaphexa.infra.entity;

import br.com.fiaphexa.aplicacao.dtos.pedido.PedidoDto;
import br.com.fiaphexa.dominio.enuns.StatusPedido;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "pedido")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private ClienteEntity cliente;

    @ManyToMany
    @JoinTable(name = "pedido_produto",
            joinColumns = @JoinColumn(name = "pedido_id"),
            inverseJoinColumns = @JoinColumn(name = "produto_id"))
    private List<ProdutoEntity> produtos;

    @Column(name = "data_pedido")
    LocalDateTime dataPedido;

    @Enumerated(EnumType.STRING)
    private StatusPedido statusPedido;

    @NotNull
    private Boolean isApproved;

    public PedidoEntity(PedidoDto pedidoDto) {
        this.id = pedidoDto.id();
        this.cliente = new ClienteEntity(pedidoDto.clienteDto());
        this.produtos = pedidoDto.produtosDtos().stream().map(ProdutoEntity::new).toList();
        this.dataPedido = pedidoDto.dataPedido();
        this.statusPedido = pedidoDto.statusPedido();
        this.isApproved = pedidoDto.isApproved();
    }

    public PedidoEntity(ClienteEntity cliente, List<ProdutoEntity> produtos, StatusPedido statusPedido, Boolean isApproved) {
        this.cliente = cliente;
        this.produtos = produtos;
        this.dataPedido = LocalDateTime.now();
        this.statusPedido = statusPedido;
        this.isApproved = isApproved;
    }

    public PedidoDto toPedidoDto() {
        return new PedidoDto(
                this.id,
                this.cliente.toClienteDto(),
                this.produtos.stream().map(ProdutoEntity::toProdutoDto).toList(),
                this.dataPedido,
                this.statusPedido,
                this.isApproved
        );
    }
}
