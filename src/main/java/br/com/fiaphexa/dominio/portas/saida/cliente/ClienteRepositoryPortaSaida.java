package br.com.fiaphexa.dominio.portas.saida.cliente;

import br.com.fiaphexa.dominio.dtos.cliente.ClienteDto;

import java.util.Optional;

public interface ClienteRepositoryPortaSaida {

    ClienteDto salvaCliente(ClienteDto clienteDto);

    Optional<ClienteDto> procuraClientePorCpf(String cpf);

    void removeCliente(String cpf);

    ClienteDto atualizaCliente(ClienteDto clienteDto);
}
