package br.com.fiaphexa.aplicacao.casosdeuso.abstracoes.pedidos;

import br.com.fiaphexa.web.controllers.pedido.request.PagamentoRequestDto;

public interface ConsultaStatusPagamentoCasoDeUso {

    public String consultaStatusPagamento(PagamentoRequestDto requestDto);
}
