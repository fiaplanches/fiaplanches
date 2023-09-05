package br.com.fiaphexa.infra.gateways;

import br.com.fiaphexa.aplicacao.dtos.pedido.EnviaPagamentoDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

@HttpExchange
public interface WebhookGateway {

    @PostExchange("/api/v1/pagamentos")
    ResponseEntity<HttpStatus> enviaPagamento(@RequestBody EnviaPagamentoDto requestDto);
}
