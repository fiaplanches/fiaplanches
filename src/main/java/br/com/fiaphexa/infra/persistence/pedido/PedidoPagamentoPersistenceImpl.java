package br.com.fiaphexa.infra.persistence.pedido;

import br.com.fiaphexa.aplicacao.dtos.pedido.CheckoutPedidoDto;
import br.com.fiaphexa.aplicacao.repositorios.pedido.PedidoPagamentoRepositoryService;

import java.math.BigDecimal;
import java.util.Random;

public class PedidoPagamentoPersistenceImpl implements PedidoPagamentoRepositoryService {
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

