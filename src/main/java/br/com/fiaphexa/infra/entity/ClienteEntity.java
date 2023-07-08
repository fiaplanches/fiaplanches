package br.com.fiaphexa.infra.entity;

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
}
