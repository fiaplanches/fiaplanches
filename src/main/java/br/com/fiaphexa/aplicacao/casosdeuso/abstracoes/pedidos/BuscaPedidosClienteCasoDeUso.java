package br.com.fiaphexa.aplicacao.casosdeuso.abstracoes.pedidos;

import br.com.fiaphexa.aplicacao.dtos.PageInfoDto;
import br.com.fiaphexa.aplicacao.dtos.pedido.PedidoDto;

import java.util.List;

public interface BuscaPedidosClienteCasoDeUso {

    public List<PedidoDto> buscaPedidosCliente(String cpf, PageInfoDto page);
}
