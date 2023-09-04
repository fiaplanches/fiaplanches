package br.com.fiaphexa.aplicacao.casosdeuso.pedido;

import br.com.fiaphexa.aplicacao.casosdeuso.abstracoes.pedidos.ConsultaStatusPagamentoCasoDeUso;
import br.com.fiaphexa.aplicacao.repositorios.cliente.ClienteRepositoryService;
import br.com.fiaphexa.aplicacao.repositorios.pedido.PedidoRepositoryService;
import br.com.fiaphexa.web.controllers.pedido.request.PagamentoRequestDto;
import jakarta.persistence.EntityNotFoundException;

public class ConsultaStatusPagamentoCasoDeUsoImpl implements ConsultaStatusPagamentoCasoDeUso {

    private final PedidoRepositoryService pedidoRepositoryService;

    private final ClienteRepositoryService clienteRepositoryService;

    public ConsultaStatusPagamentoCasoDeUsoImpl(PedidoRepositoryService pedidoRepositoryService,
                                                ClienteRepositoryService clienteRepositoryService) {
        this.pedidoRepositoryService = pedidoRepositoryService;
        this.clienteRepositoryService = clienteRepositoryService;
    }

    @Override
    public String consultaStatusPagamento(PagamentoRequestDto request) {

        clienteRepositoryService.procuraClientePorCpf(request.cpf())
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));

        var pedidoDto = pedidoRepositoryService.buscaPedidoPorId(request.idPedido())
                .orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado"));

        return pedidoDto.statusToString();

    }
}
