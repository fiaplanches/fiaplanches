package br.com.fiaphexa.dominio.services.pedido;

import br.com.fiaphexa.dominio.dtos.PageInfoDto;
import br.com.fiaphexa.dominio.dtos.pedido.PedidoDto;
import br.com.fiaphexa.dominio.portas.entrada.pedidos.BuscaPedidosPortaEntrada;
import br.com.fiaphexa.dominio.portas.saida.pedido.PedidoRepositoryPortaSaida;

import java.util.List;

public class BuscaPedidosService implements BuscaPedidosPortaEntrada {

    private final PedidoRepositoryPortaSaida pedidoRepositoryPortaSaida;

    public BuscaPedidosService(PedidoRepositoryPortaSaida pedidoRepositoryPortaSaida) {
        this.pedidoRepositoryPortaSaida = pedidoRepositoryPortaSaida;
    }

    @Override
    public List<PedidoDto> buscaPedidos(PageInfoDto page) {
        return pedidoRepositoryPortaSaida.buscaPedidos(page);
    }
}
