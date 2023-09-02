package br.com.fiaphexa.web.controllers.cliente.request;

import br.com.fiaphexa.aplicacao.dtos.cliente.ClienteDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

public record ClienteRequestDto(
        @CPF(message = "CPF informado invalido")
        @NotBlank(message = "Número do CPF não pode ser vazio")
        String cpf,
        @Size(max = 100)
        @NotBlank(message = "Nome não pode ser vazio")
        String nome
) {

    public ClienteDto toClienteDto() {
        return new ClienteDto(null, this.cpf, this.nome);
    }
}
