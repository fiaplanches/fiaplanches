package br.com.fiaphexa.aplicacao.casosdeuso.abstracoes.pedidos;

import br.com.fiaphexa.aplicacao.dtos.pedido.PedidoComIdProdutosDto;
import br.com.fiaphexa.aplicacao.dtos.pedido.PedidoDto;

public interface CriaPedidoCasoDeUso {

    public PedidoDto criaPedido(PedidoComIdProdutosDto pedidoComIdProdutosDto);
}
