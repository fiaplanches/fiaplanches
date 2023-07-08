package br.com.fiaphexa.aplicacao.controllers.cliente.response;

import br.com.fiaphexa.aplicacao.controllers.cliente.request.ClienteRequestDto;
import br.com.fiaphexa.dominio.dtos.cliente.ClienteDto;

public record ClienteResponseDto(
        Long id,
        Long cpf,
        String nome
) {

    public ClienteResponseDto(Long id, Long cpf, String nome) {
        this.id = id;
        this.cpf = cpf;
        this.nome = nome;
    }

    public static ClienteResponseDto toClienteResponseDto(ClienteDto clienteDto) {
        return new ClienteResponseDto(
                clienteDto.id(),
                clienteDto.cpf(),
                clienteDto.nome()
        );
    }
}
