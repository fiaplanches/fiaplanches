package br.com.fiaphexa.aplicacao.casosdeuso.pedido;

import br.com.fiaphexa.aplicacao.dtos.pedido.EnviaPagamentoDto;
import br.com.fiaphexa.dominio.enuns.MetodoPagamentoEnum;
import br.com.fiaphexa.aplicacao.casosdeuso.abstracoes.pedidos.PagaPedidoCasoDeUso;
import br.com.fiaphexa.aplicacao.repositorios.cliente.ClienteRepositoryService;
import br.com.fiaphexa.aplicacao.repositorios.pedido.PedidoRepositoryService;
import br.com.fiaphexa.infra.gateways.WebhookGateway;
import br.com.fiaphexa.web.controllers.pedido.request.PagamentoRequestDto;
import jakarta.persistence.EntityNotFoundException;

public class PagaPedidoCasoDeUsoImpl implements PagaPedidoCasoDeUso {

    private final PedidoRepositoryService pedidoRepositoryService;

    private final ClienteRepositoryService clienteRepositoryService;

    private final WebhookGateway webhookGateway;

    public PagaPedidoCasoDeUsoImpl(PedidoRepositoryService pedidoRepositoryService,
                                   ClienteRepositoryService clienteRepositoryService,
                                   WebhookGateway webhookGateway) {
        this.pedidoRepositoryService = pedidoRepositoryService;
        this.clienteRepositoryService = clienteRepositoryService;
        this.webhookGateway = webhookGateway;
    }

    @Override
    public void pagaPedido(PagamentoRequestDto pagamentoRequestDto) {

        clienteRepositoryService.procuraClientePorCpf(pagamentoRequestDto.cpf())
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));

        var pedidoDto = pedidoRepositoryService.buscaPedidoPorId(pagamentoRequestDto.idPedido())
                .orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado"));

        webhookGateway.enviaPagamento(new EnviaPagamentoDto(MetodoPagamentoEnum.PIX, Long.toString(pedidoDto.id())));
    }

//    private BigDecimal calculaValorTotal(List<Produto> produtos) {
//        return produtos.stream().map(Produto::getPreco).reduce(BigDecimal.ZERO, BigDecimal::add);
//    }
}
