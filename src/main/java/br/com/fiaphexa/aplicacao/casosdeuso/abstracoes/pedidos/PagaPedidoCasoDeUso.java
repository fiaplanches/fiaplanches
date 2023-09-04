package br.com.fiaphexa.aplicacao.casosdeuso.abstracoes.pedidos;

import br.com.fiaphexa.web.controllers.pedido.request.PagamentoRequestDto;
import org.springframework.http.ResponseEntity;

public interface PagaPedidoCasoDeUso {

    public void pagaPedido(PagamentoRequestDto pagamentoRequestDto);
}
