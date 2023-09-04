package br.com.fiaphexa.aplicacao.casosdeuso.pedido;

import br.com.fiaphexa.aplicacao.casosdeuso.abstracoes.pedidos.AtualizaStatusPagamentoCasoDeUso;
import br.com.fiaphexa.aplicacao.repositorios.pedido.PedidoRepositoryService;
import br.com.fiaphexa.dominio.enuns.StatusPagamentoEnum;
import br.com.fiaphexa.web.controllers.pedido.request.AtualizaStatusPagamentoRequestDto;

public class AtualizaStatusPagamentoCasoDeUsoImpl implements AtualizaStatusPagamentoCasoDeUso {

    private final PedidoRepositoryService repositoryService;

    public AtualizaStatusPagamentoCasoDeUsoImpl(PedidoRepositoryService repositoryService) {
        this.repositoryService = repositoryService;
    }

    @Override
    public boolean atualizaStatusPagamento(AtualizaStatusPagamentoRequestDto requestDto) {

        boolean status = requestDto.status() == StatusPagamentoEnum.APPROVED;

        return repositoryService.atualizaStatusPagamento(Long.parseLong(requestDto.orderId()), status);
    }
}
