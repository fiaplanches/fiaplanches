package br.com.fiaphexa.aplicacao.casosdeuso.pedido;

import br.com.fiaphexa.aplicacao.dtos.PageInfoDto;
import br.com.fiaphexa.aplicacao.dtos.pedido.PedidoDto;
import br.com.fiaphexa.aplicacao.casosdeuso.abstracoes.pedidos.BuscaPedidosClienteCasoDeUso;
import br.com.fiaphexa.aplicacao.repositorios.pedido.PedidoRepositoryService;

import java.util.List;

public class BuscaPedidosClienteCasoDeUsoImpl implements BuscaPedidosClienteCasoDeUso {

    private final PedidoRepositoryService pedidoRepositoryService;

    public BuscaPedidosClienteCasoDeUsoImpl(PedidoRepositoryService pedidoRepositoryService) {
        this.pedidoRepositoryService = pedidoRepositoryService;
    }

    @Override
    public List<PedidoDto> buscaPedidosCliente(String cpf, PageInfoDto page) {
        return pedidoRepositoryService.buscaPedidosCliente(cpf, page);
    }
}
