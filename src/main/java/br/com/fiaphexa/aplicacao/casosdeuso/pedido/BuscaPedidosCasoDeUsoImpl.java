package br.com.fiaphexa.aplicacao.casosdeuso.pedido;

import br.com.fiaphexa.aplicacao.dtos.PageInfoDto;
import br.com.fiaphexa.aplicacao.dtos.pedido.PedidoDto;
import br.com.fiaphexa.aplicacao.casosdeuso.abstracoes.pedidos.BuscaPedidosCasoDeUso;
import br.com.fiaphexa.aplicacao.repositorios.pedido.PedidoRepositoryService;

import java.util.List;

public class BuscaPedidosCasoDeUsoImpl implements BuscaPedidosCasoDeUso {

    private final PedidoRepositoryService pedidoRepositoryService;

    public BuscaPedidosCasoDeUsoImpl(PedidoRepositoryService pedidoRepositoryService) {
        this.pedidoRepositoryService = pedidoRepositoryService;
    }

    @Override
    public List<PedidoDto> buscaPedidos(PageInfoDto page) {
        return pedidoRepositoryService.buscaPedidos(page);
    }
}
