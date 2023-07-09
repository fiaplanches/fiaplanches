package br.com.fiaphexa.dominio.services.cliente;

import br.com.fiaphexa.dominio.dtos.cliente.ClienteDto;
import br.com.fiaphexa.dominio.portas.entrada.clientes.AtualizaClientePortaEntrada;
import br.com.fiaphexa.dominio.portas.saida.cliente.ClienteRepositoryPortaSaida;

public class AtualizaClienteService implements AtualizaClientePortaEntrada {

    private final ClienteRepositoryPortaSaida clienteRepositoryPortaSaida;

    public AtualizaClienteService(ClienteRepositoryPortaSaida clienteRepositoryPortaSaida) {
        this.clienteRepositoryPortaSaida = clienteRepositoryPortaSaida;
    }

    @Override
    public ClienteDto atualiza(ClienteDto clienteDto) {
        return clienteRepositoryPortaSaida.atualizaCliente(clienteDto);
    }
}
