package br.com.fiaphexa.aplicacao.excecoes.erros;

public class BadRequestException extends RuntimeException{

    public BadRequestException(String message) {
        super(message);
    }
}
