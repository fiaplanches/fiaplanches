package br.com.fiaphexa.aplicacao.controllers.cliente.response;

import br.com.fiaphexa.dominio.dtos.cliente.ClienteDto;

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
