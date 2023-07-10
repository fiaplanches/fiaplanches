package br.com.fiaphexa.dominio.services.cliente;

import br.com.fiaphexa.dominio.portas.entrada.clientes.RemoveClientePortaEntrada;
import br.com.fiaphexa.dominio.portas.saida.cliente.ClienteRepositoryPortaSaida;

public class RemoveClienteService implements RemoveClientePortaEntrada {


    private final ClienteRepositoryPortaSaida clienteRepositoryPortaSaida;

    public RemoveClienteService(ClienteRepositoryPortaSaida clienteRepositoryPortaSaida) {
        this.clienteRepositoryPortaSaida = clienteRepositoryPortaSaida;
    }

    @Override
    public void remove(String cpf) {
        clienteRepositoryPortaSaida.removeCliente(cpf);
    }
}
