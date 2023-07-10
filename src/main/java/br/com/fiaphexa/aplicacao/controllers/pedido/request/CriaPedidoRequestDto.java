package br.com.fiaphexa.aplicacao.controllers.pedido.request;

import br.com.fiaphexa.dominio.dtos.pedido.PedidoComIdProdutosDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CriaPedidoRequestDto(
        @NotBlank(message = "CPF nao pode ser vazio")
        String cpf,
        @NotNull(message = "Produtos nao pode ser nulus")
        List<Long> produtos
) {

    public static PedidoComIdProdutosDto toPedidoComIdProdutosDto(CriaPedidoRequestDto criaPedidoRequestDto) {
        return new PedidoComIdProdutosDto(
                criaPedidoRequestDto.cpf(),
                criaPedidoRequestDto.produtos()
        );
    }
}
