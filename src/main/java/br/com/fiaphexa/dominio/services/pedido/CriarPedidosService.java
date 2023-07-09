package br.com.fiaphexa.dominio.services.pedido;

import br.com.fiaphexa.dominio.dtos.pedido.PedidoDto;
import br.com.fiaphexa.dominio.portas.entrada.pedidos.CriaPedidoPortaEntrada;
import br.com.fiaphexa.dominio.portas.saida.pedido.PedidoRepositoryPortaSaida;

public class CriarPedidosService implements CriaPedidoPortaEntrada {

    private final PedidoRepositoryPortaSaida pedidoRepositoryPortaSaida;

    public CriarPedidosService(PedidoRepositoryPortaSaida pedidoRepositoryPortaSaida) {
        this.pedidoRepositoryPortaSaida = pedidoRepositoryPortaSaida;
    }

    @Override
    public PedidoDto criaPedido(PedidoDto pedidoDto) {
        return pedidoRepositoryPortaSaida.criaPedido(pedidoDto);
    }
}
