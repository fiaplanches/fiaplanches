package br.com.fiaphexa.aplicacao.dtos.cliente;

import br.com.fiaphexa.dominio.model.Cliente;

public record ClienteDto(
        Long id,
        String cpf,
        String nome
) {

    public static ClienteDto toClienteDto(Cliente cliente) {
        return new ClienteDto(
                cliente.getId(),
                cliente.getCpf(),
                cliente.getNome()
        );
    }

    public Cliente toCliente() {
        return new Cliente(
                this.id,
                this.cpf,
                this.nome
        );
    }

}
