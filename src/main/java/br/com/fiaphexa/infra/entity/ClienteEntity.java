package br.com.fiaphexa.infra.entity;

import br.com.fiaphexa.dominio.dtos.cliente.ClienteDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity(name = "Cliente")
@Table(name = "cliente")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class ClienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cpf;

    private String nome;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClienteEntity that = (ClienteEntity) o;
        return Objects.equals(cpf, that.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf);
    }
}
