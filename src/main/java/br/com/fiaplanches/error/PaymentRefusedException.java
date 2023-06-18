package br.com.fiaplanches.error;

public class PaymentRefusedException extends RuntimeException{

        public PaymentRefusedException(String message) {
            super(message);
        }
}
