package br.com.fiaphexa.aplicacao.casosdeuso.abstracoes.clientes;

import br.com.fiaphexa.aplicacao.dtos.cliente.ClienteDto;

public interface ProcuraClienteCasoDeUso {

    ClienteDto procuraPorCpf(String cpf);
}
