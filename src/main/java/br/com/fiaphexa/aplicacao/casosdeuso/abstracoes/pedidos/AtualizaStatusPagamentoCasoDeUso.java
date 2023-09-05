package br.com.fiaphexa.aplicacao.casosdeuso.abstracoes.pedidos;

import br.com.fiaphexa.web.controllers.pedido.request.AtualizaStatusPagamentoRequestDto;

public interface AtualizaStatusPagamentoCasoDeUso {

    public boolean atualizaStatusPagamento(AtualizaStatusPagamentoRequestDto requestDto);
}
