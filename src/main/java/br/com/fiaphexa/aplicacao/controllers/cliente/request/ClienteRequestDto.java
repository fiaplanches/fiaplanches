package br.com.fiaphexa.aplicacao.controllers.cliente.request;

import br.com.fiaphexa.dominio.dtos.cliente.ClienteDto;

public record ClienteRequestDto(
    Long cpf,
    String nome
) {

    public ClienteDto toClienteDto() {
        return new ClienteDto(
                null,
                this.cpf,
                this.nome
        );
    }
}
