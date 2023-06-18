package br.com.fiaplanches.records;

import br.com.fiaplanches.model.Cliente;

public record RetornoCliente(Long id, Long cpf, String nome) {

    public RetornoCliente(Cliente cliente) {
        this(cliente.getId(), cliente.getCpf(), cliente.getNome());
    }
}
