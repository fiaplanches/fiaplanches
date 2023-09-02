package br.com.fiaphexa.aplicacao.casosdeuso.cliente;

import br.com.fiaphexa.aplicacao.dtos.cliente.ClienteDto;
import br.com.fiaphexa.aplicacao.casosdeuso.abstracoes.clientes.ProcuraClienteCasoDeUso;
import br.com.fiaphexa.aplicacao.repositorios.cliente.ClienteRepositoryService;

public class ProcuraClienteCasoDeUsoImpl implements ProcuraClienteCasoDeUso {

    private final ClienteRepositoryService clienteRepositoryService;

    public ProcuraClienteCasoDeUsoImpl(ClienteRepositoryService clienteRepositoryService) {
        this.clienteRepositoryService = clienteRepositoryService;
    }

    @Override
    public ClienteDto procuraPorCpf(String cpf) {
        return clienteRepositoryService.procuraClientePorCpf(cpf)
                .orElseThrow(() -> new RuntimeException("Cliente nao localizado"));
    }
}
