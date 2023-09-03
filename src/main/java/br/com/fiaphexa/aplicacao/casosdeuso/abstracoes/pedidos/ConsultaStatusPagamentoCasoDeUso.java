package br.com.fiaphexa.aplicacao.casosdeuso.abstracoes.pedidos;

import br.com.fiaphexa.web.controllers.pedido.request.ConsultaStatusPagamentoRequestDto;

public interface ConsultaStatusPagamentoCasoDeUso {

    public String consultaStatusPagamento(ConsultaStatusPagamentoRequestDto requestDto);
}
