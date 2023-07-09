package br.com.fiaphexa.dominio.portas.saida.cliente;

import br.com.fiaphexa.dominio.dtos.cliente.ClienteDto;

public interface ClienteRepositoryPortaSaida {

    ClienteDto salvaCliente(ClienteDto clienteDto);

    ClienteDto procuraClientePorCpf(String cpf);

    void removeCliente(String cpf);

    ClienteDto atualizaCliente(ClienteDto clienteDto);
}
