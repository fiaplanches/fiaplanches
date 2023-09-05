package br.com.fiaphexa.aplicacao.casosdeuso.abstracoes.pedidos;

import br.com.fiaphexa.aplicacao.dtos.pedido.PedidoDto;

import java.util.List;

public interface BuscaPedidosOrdenadosCasoDeUso {

    List<PedidoDto> buscaPedidosOrdenados();
}
