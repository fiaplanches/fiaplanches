package br.com.fiaphexa.aplicacao.casosdeuso.cliente;

import br.com.fiaphexa.aplicacao.dtos.cliente.ClienteDto;
import br.com.fiaphexa.aplicacao.casosdeuso.abstracoes.clientes.CadastraClienteCasoDeUso;
import br.com.fiaphexa.aplicacao.repositorios.cliente.ClienteRepositoryService;

public class CadastraClienteCasoDeUsoImpl implements CadastraClienteCasoDeUso {

    private final ClienteRepositoryService clienteRepositoryService;

    public CadastraClienteCasoDeUsoImpl(ClienteRepositoryService clienteRepositoryService) {
        this.clienteRepositoryService = clienteRepositoryService;
    }

    @Override
    public ClienteDto cadastra(ClienteDto clienteDto) {
        var cliente = clienteRepositoryService.procuraClientePorCpf(clienteDto.cpf());
        if (cliente.isEmpty()) {
            return clienteRepositoryService.salvaCliente(clienteDto);
        } else {
            throw new RuntimeException("Cliente ja cadastrado");
        }
    }
}
