package br.com.fiaphexa.aplicacao.casosdeuso.cliente;

import br.com.fiaphexa.aplicacao.casosdeuso.abstracoes.clientes.RemoveClienteCasoDeUso;
import br.com.fiaphexa.aplicacao.repositorios.cliente.ClienteRepositoryService;
import br.com.fiaphexa.aplicacao.repositorios.pedido.PedidoRepositoryService;
import jakarta.persistence.EntityNotFoundException;

public class RemoveClienteCasoDeUsoImpl implements RemoveClienteCasoDeUso {


    private final ClienteRepositoryService clienteRepositoryService;

    private final PedidoRepositoryService pedidoRepositoryService;

    public RemoveClienteCasoDeUsoImpl(ClienteRepositoryService clienteRepositoryService, PedidoRepositoryService pedidoRepositoryService) {
        this.clienteRepositoryService = clienteRepositoryService;
        this.pedidoRepositoryService = pedidoRepositoryService;
    }

    @Override
    public void remove(String cpf) {
        var clienteDto = clienteRepositoryService.procuraClientePorCpf(cpf)
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));
        var pedidosDtosList = pedidoRepositoryService.buscaTodosPedidosPorCpf(cpf);

        pedidoRepositoryService.removePedidos(pedidosDtosList);
        clienteRepositoryService.removeCliente(cpf);
    }
}
