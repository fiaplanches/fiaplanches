package br.com.fiaphexa.web.controllers.pedido.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

public record PagamentoRequestDto(

        @NotBlank(message = "CPF nao pode ser vazio")
        @CPF(message = "CPF informado é invalido")
        String cpf,
        @NotNull(message = "Id do Pedido não pode ser vazio ou nulo")
        Long idPedido
) {

}
