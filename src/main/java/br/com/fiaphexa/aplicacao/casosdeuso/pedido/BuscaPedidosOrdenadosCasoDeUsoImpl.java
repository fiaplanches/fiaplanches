package br.com.fiaphexa.aplicacao.casosdeuso.pedido;

import br.com.fiaphexa.aplicacao.casosdeuso.abstracoes.pedidos.BuscaPedidosOrdenadosCasoDeUso;
import br.com.fiaphexa.aplicacao.dtos.pedido.PedidoDto;
import br.com.fiaphexa.aplicacao.repositorios.pedido.PedidoRepositoryService;

import java.util.List;

public class BuscaPedidosOrdenadosCasoDeUsoImpl implements BuscaPedidosOrdenadosCasoDeUso {

    private final PedidoRepositoryService pedidoRepositoryService;
    public BuscaPedidosOrdenadosCasoDeUsoImpl(PedidoRepositoryService pedidoRepositoryService) {
        this.pedidoRepositoryService = pedidoRepositoryService;
    }

    @Override
    public List<PedidoDto> buscaPedidosOrdenados() {
        return pedidoRepositoryService.buscaPedidosOrdenados();
    }
}
