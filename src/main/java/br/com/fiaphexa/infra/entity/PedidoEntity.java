package br.com.fiaphexa.infra.entity;

import br.com.fiaphexa.dominio.dtos.pedido.PedidoDto;
import br.com.fiaphexa.dominio.enuns.StatusPedido;
import jakarta.persistence.*;
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

    public PedidoEntity(PedidoDto pedidoDto) {
        this.id = pedidoDto.id();
        this.cliente = new ClienteEntity(pedidoDto.clienteDto());
        this.produtos = pedidoDto.produtosDtos().stream().map(ProdutoEntity::new).toList();
        this.dataPedido = pedidoDto.dataPedido();
        this.statusPedido = pedidoDto.statusPedido();
    }
}
