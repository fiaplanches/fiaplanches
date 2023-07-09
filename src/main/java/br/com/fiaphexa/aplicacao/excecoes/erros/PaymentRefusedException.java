package br.com.fiaphexa.aplicacao.excecoes.erros;

public class PaymentRefusedException extends RuntimeException{

        public PaymentRefusedException(String message) {
            super(message);
        }
}
