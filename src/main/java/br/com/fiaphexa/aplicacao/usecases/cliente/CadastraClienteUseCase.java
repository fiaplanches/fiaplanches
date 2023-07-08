package br.com.fiaphexa.aplicacao.usecases.cliente;

import br.com.fiaphexa.dominio.dtos.cliente.ClienteDto;
import br.com.fiaphexa.dominio.portas.entrada.clientes.CadastraClientePortaEntrada;
import br.com.fiaphexa.dominio.portas.saida.cliente.ClienteRepositoryPortaSaida;

public class CadastraClienteUseCase implements CadastraClientePortaEntrada {

    private final ClienteRepositoryPortaSaida clienteRepositoryPortaSaida;

    public CadastraClienteUseCase(ClienteRepositoryPortaSaida clienteRepositoryPortaSaida) {
        this.clienteRepositoryPortaSaida = clienteRepositoryPortaSaida;
    }

    @Override
    public ClienteDto cadastra(ClienteDto clienteDto) {
        return clienteRepositoryPortaSaida.salvaCliente(clienteDto);
    }
}
