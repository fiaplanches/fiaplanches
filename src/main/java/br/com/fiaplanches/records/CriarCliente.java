package br.com.fiaplanches.records;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CriarCliente(
        @Min(value = 10000000000L, message = "CPF inválido, menor que 11 dígitos")
        @Max(value = 99999999999L, message = "CPF inválido, maior que 11 dígitos")
        @NotNull(message = "CPF e obrigatorio!")
        Long cpf,
        @NotBlank(message = "Nome e obrigatorio!")
        String nome
) {
}
