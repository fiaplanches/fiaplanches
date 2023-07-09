package br.com.fiaphexa.dominio.portas.entrada.clientes;

import br.com.fiaphexa.dominio.dtos.cliente.ClienteDto;

public interface AtualizaClientePortaEntrada {

    ClienteDto atualiza(ClienteDto clienteDto);
}
