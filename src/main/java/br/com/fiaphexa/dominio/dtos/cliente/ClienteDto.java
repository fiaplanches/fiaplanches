package br.com.fiaphexa.dominio.dtos.cliente;

import br.com.fiaphexa.dominio.model.Cliente;

public record ClienteDto(
        Long id,
        Long cpf,
        String nome
) {

    public ClienteDto(Long id, Long cpf, String nome) {
        this.id = id;
        this.cpf = cpf;
        this.nome = nome;
    }

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
