package br.com.fiaphexa.infra.entity;

import br.com.fiaphexa.dominio.dtos.cliente.ClienteDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Entity(name = "Cliente")
@Table(name = "cliente")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class ClienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long cpf;

    private String nome;

    @OneToMany
    @JoinColumn(name = "cliente_id")
    @JsonIgnore
    private List<PedidoEntity> pedidoEntities;

    public ClienteEntity(ClienteDto clienteDto) {
        this.id = clienteDto.id();
        this.cpf = clienteDto.cpf();
        this.nome = clienteDto.nome();
    }

    public ClienteDto toClienteDto() {
        return new ClienteDto(
                this.id,
                this.cpf,
                this.nome
        );
    }
}
