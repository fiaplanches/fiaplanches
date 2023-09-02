package br.com.fiaphexa.aplicacao.casosdeuso.abstracoes.pedidos;

import br.com.fiaphexa.aplicacao.dtos.PageInfoDto;
import br.com.fiaphexa.aplicacao.dtos.pedido.PedidoDto;

import java.util.List;

public interface BuscaPedidosCasoDeUso {

    List<PedidoDto> buscaPedidos(PageInfoDto page);

}
