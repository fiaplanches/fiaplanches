package br.com.fiaphexa.dominio.services.cliente;

import br.com.fiaphexa.dominio.dtos.cliente.ClienteDto;
import br.com.fiaphexa.dominio.portas.entrada.clientes.CadastraClientePortaEntrada;
import br.com.fiaphexa.dominio.portas.saida.cliente.ClienteRepositoryPortaSaida;

public class CadastraClienteService implements CadastraClientePortaEntrada {

    private final ClienteRepositoryPortaSaida clienteRepositoryPortaSaida;

    public CadastraClienteService(ClienteRepositoryPortaSaida clienteRepositoryPortaSaida) {
        this.clienteRepositoryPortaSaida = clienteRepositoryPortaSaida;
    }

    @Override
    public ClienteDto cadastra(ClienteDto clienteDto) {
        var cliente = clienteRepositoryPortaSaida.procuraClientePorCpf(clienteDto.cpf());
        if (cliente.isEmpty()) {
            return clienteRepositoryPortaSaida.salvaCliente(clienteDto);
        } else {
            throw new RuntimeException("Cliente ja cadastrado");
        }
    }
}
