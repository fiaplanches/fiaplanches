package br.com.fiaphexa.infra.adapter.pedido;

import br.com.fiaphexa.dominio.dtos.pedido.CheckoutPedidoDto;
import br.com.fiaphexa.dominio.portas.saida.pedido.PedidoPagamentoPortaSaida;

import java.math.BigDecimal;
import java.util.Random;

public class PedidoPagamentoAdapter implements PedidoPagamentoPortaSaida {
    @Override
    public CheckoutPedidoDto pagamentoPedido(String cpf, BigDecimal valorTotal) {
        Random random = new Random();
        int chance = random.nextInt(100) + 1;

        if (chance <= 5) {
            return new CheckoutPedidoDto(false, "Account funds insufficient.");
        } else if (chance <= 10) {
            return new CheckoutPedidoDto(false, "Network error occurred.");
        }

        return new CheckoutPedidoDto(true, "Payment successful.");
    }
}

