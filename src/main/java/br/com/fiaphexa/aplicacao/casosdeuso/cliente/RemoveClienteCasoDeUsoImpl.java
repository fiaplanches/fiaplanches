package br.com.fiaphexa.aplicacao.casosdeuso.cliente;

import br.com.fiaphexa.aplicacao.casosdeuso.abstracoes.clientes.RemoveClienteCasoDeUso;
import br.com.fiaphexa.aplicacao.repositorios.cliente.ClienteRepositoryService;

public class RemoveClienteCasoDeUsoImpl implements RemoveClienteCasoDeUso {


    private final ClienteRepositoryService clienteRepositoryService;

    public RemoveClienteCasoDeUsoImpl(ClienteRepositoryService clienteRepositoryService) {
        this.clienteRepositoryService = clienteRepositoryService;
    }

    @Override
    public void remove(String cpf) {
        clienteRepositoryService.removeCliente(cpf);
    }
}
