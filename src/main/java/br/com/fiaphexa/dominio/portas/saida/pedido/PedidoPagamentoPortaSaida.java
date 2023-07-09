package br.com.fiaphexa.dominio.portas.saida.pedido;

import br.com.fiaphexa.dominio.dtos.pedido.CheckoutPedidoDto;

import java.math.BigDecimal;

public interface PedidoPagamentoPortaSaida {

    public CheckoutPedidoDto pagamentoPedido(String cpf, BigDecimal valorTotal);
}
