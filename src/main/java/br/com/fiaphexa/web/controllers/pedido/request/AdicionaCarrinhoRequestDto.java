package br.com.fiaphexa.web.controllers.pedido.request;

import br.com.fiaphexa.aplicacao.dtos.pedido.PedidoComIdProdutosDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

import java.util.List;

public record AdicionaCarrinhoRequestDto(
        @NotBlank(message = "CPF nao pode ser vazio")
        @CPF(message = "CPF informado e invalido")
        String cpf,
        @NotNull(message = "Produtos nao pode ser nulus")
        List<Long> produtos
) {

    public static PedidoComIdProdutosDto toPedidoComIdProdutosDto(AdicionaCarrinhoRequestDto adicionaCarrinhoRequestDto) {
        return new PedidoComIdProdutosDto(
                adicionaCarrinhoRequestDto.cpf(),
                adicionaCarrinhoRequestDto.produtos()
        );
    }
}
