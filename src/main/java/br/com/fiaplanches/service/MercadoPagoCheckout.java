package br.com.fiaplanches.service;

import br.com.fiaplanches.records.CheckoutRecord;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Random;

@Service
public class MercadoPagoCheckout {

    public CheckoutRecord checkout(Long cpf, BigDecimal valorTotal) {

        Random random = new Random();
        int chance = random.nextInt(100) + 1;

        if (chance <= 5) {
            return new CheckoutRecord(false, "Account funds insufficient.");
        } else if (chance <= 10) {
            return new CheckoutRecord(false, "Network error occurred.");
        }

        return new CheckoutRecord(true, "Payment successful.");
    }
}
