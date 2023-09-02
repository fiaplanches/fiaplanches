package br.com.fiaphexa.aplicacao.casosdeuso.cliente;

import br.com.fiaphexa.aplicacao.dtos.cliente.ClienteDto;
import br.com.fiaphexa.aplicacao.casosdeuso.abstracoes.clientes.AtualizaClienteCasoDeUso;
import br.com.fiaphexa.aplicacao.repositorios.cliente.ClienteRepositoryService;

public class AtualizaClienteCasoDeUsoImpl implements AtualizaClienteCasoDeUso {

    private final ClienteRepositoryService clienteRepositoryService;

    public AtualizaClienteCasoDeUsoImpl(ClienteRepositoryService clienteRepositoryService) {
        this.clienteRepositoryService = clienteRepositoryService;
    }

    @Override
    public ClienteDto atualiza(ClienteDto clienteDto) {
        return clienteRepositoryService.atualizaCliente(clienteDto);
    }
}
