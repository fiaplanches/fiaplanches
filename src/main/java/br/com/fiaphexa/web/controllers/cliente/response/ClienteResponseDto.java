package br.com.fiaphexa.web.controllers.cliente.response;

import br.com.fiaphexa.aplicacao.dtos.cliente.ClienteDto;

public record ClienteResponseDto(
        Long id,
        String cpf,
        String nome
) {

    public static ClienteResponseDto toClienteResponseDto(ClienteDto clienteDto) {
        return new ClienteResponseDto(
                clienteDto.id(),
                clienteDto.cpf(),
                clienteDto.nome()
        );
    }
}
