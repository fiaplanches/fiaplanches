package br.com.fiaphexa.dominio.services.cliente;

import br.com.fiaphexa.dominio.dtos.cliente.ClienteDto;
import br.com.fiaphexa.dominio.portas.entrada.clientes.ProcuraClientePortaEntrada;
import br.com.fiaphexa.dominio.portas.saida.cliente.ClienteRepositoryPortaSaida;

import java.util.Optional;

public class ProcuraClienteService implements ProcuraClientePortaEntrada {

    private final ClienteRepositoryPortaSaida clienteRepositoryPortaSaida;

    public ProcuraClienteService(ClienteRepositoryPortaSaida clienteRepositoryPortaSaida) {
        this.clienteRepositoryPortaSaida = clienteRepositoryPortaSaida;
    }

    @Override
    public ClienteDto procuraPorCpf(String cpf) {
        return clienteRepositoryPortaSaida.procuraClientePorCpf(cpf)
                .orElseThrow(() -> new RuntimeException("Cliente nao localizado"));
    }
}
