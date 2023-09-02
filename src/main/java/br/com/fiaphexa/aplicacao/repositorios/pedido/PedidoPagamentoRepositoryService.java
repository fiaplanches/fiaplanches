package br.com.fiaphexa.aplicacao.repositorios.pedido;

import br.com.fiaphexa.aplicacao.dtos.pedido.CheckoutPedidoDto;

import java.math.BigDecimal;

public interface PedidoPagamentoRepositoryService {

    public CheckoutPedidoDto pagamentoPedido(String cpf, BigDecimal valorTotal);
}
