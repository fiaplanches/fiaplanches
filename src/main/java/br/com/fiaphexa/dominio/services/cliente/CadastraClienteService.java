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
        if (clienteRepositoryPortaSaida.procuraClientePorCpf(clienteDto.cpf()) != null) {
            throw new RuntimeException("Cliente já cadastrado");
        }
        return clienteRepositoryPortaSaida.salvaCliente(clienteDto);
    }
}
