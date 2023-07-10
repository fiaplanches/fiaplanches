package br.com.fiaphexa.aplicacao.excecoes;

import br.com.fiaphexa.aplicacao.excecoes.erros.BadRequestException;
import br.com.fiaphexa.aplicacao.excecoes.erros.MessageDefaultExceptionHandler;
import br.com.fiaphexa.aplicacao.excecoes.erros.PaymentRefusedException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice(basePackages = "br.com.fiaphexa.aplicacao.controllers")
public class TratamentoExcecoes {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<MessageDefaultExceptionHandler> handlerNotFoundException(EntityNotFoundException exception){
        return new ResponseEntity<>(
                new MessageDefaultExceptionHandler(
                        exception.getMessage(),
                        HttpStatus.NOT_FOUND.value(),
                        HttpStatus.NOT_FOUND.getReasonPhrase(),
                        new Date()
                ),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<MessageDefaultExceptionHandler> handlerNotFoundException(RuntimeException exception){
        return new ResponseEntity<>(
                new MessageDefaultExceptionHandler(
                        exception.getMessage(),
                        HttpStatus.NOT_FOUND.value(),
                        HttpStatus.NOT_FOUND.getReasonPhrase(),
                        new Date()
                ),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(PaymentRefusedException.class)
    public ResponseEntity<MessageDefaultExceptionHandler> handlerPaymentRefusedException(PaymentRefusedException exception) {
        return new ResponseEntity<>(
                new MessageDefaultExceptionHandler(
                        exception.getMessage(),
                        HttpStatus.BAD_REQUEST.value(),
                        HttpStatus.BAD_REQUEST.getReasonPhrase(),
                        new Date()
                ),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<MessageDefaultExceptionHandler> handlerBadRequestException(BadRequestException exception) {
        return new ResponseEntity<>(
                new MessageDefaultExceptionHandler(
                        exception.getMessage(),
                        HttpStatus.BAD_REQUEST.value(),
                        HttpStatus.BAD_REQUEST.getReasonPhrase(),
                        new Date()
                ),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MessageDefaultExceptionHandler> handlerMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        var errors = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();

        return ResponseEntity.badRequest().body(
                new MessageDefaultExceptionHandler(
                        errors,
                        HttpStatus.BAD_REQUEST.value(),
                        HttpStatus.BAD_REQUEST.getReasonPhrase(),
                        new Date()
                )
        );
    }
}
