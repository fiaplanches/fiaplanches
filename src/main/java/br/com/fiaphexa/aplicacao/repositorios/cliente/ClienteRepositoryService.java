package br.com.fiaphexa.aplicacao.repositorios.cliente;

import br.com.fiaphexa.aplicacao.dtos.cliente.ClienteDto;

import java.util.Optional;

public interface ClienteRepositoryService {

    ClienteDto salvaCliente(ClienteDto clienteDto);

    Optional<ClienteDto> procuraClientePorCpf(String cpf);

    void removeCliente(String cpf);
}
