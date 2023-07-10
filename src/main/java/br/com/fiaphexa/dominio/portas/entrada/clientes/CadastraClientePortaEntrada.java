package br.com.fiaphexa.dominio.portas.entrada.clientes;

import br.com.fiaphexa.dominio.dtos.cliente.ClienteDto;

public interface CadastraClientePortaEntrada {

    ClienteDto cadastra(ClienteDto clienteDto);
}
