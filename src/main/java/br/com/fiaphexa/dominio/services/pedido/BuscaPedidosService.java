package br.com.fiaphexa.dominio.services.pedido;

import br.com.fiaphexa.dominio.dtos.pedido.PedidoDto;
import br.com.fiaphexa.dominio.portas.entrada.pedidos.BuscaPedidosPortaEntrada;
import br.com.fiaphexa.dominio.portas.saida.cliente.ClienteRepositoryPortaSaida;
import br.com.fiaphexa.dominio.portas.saida.pedido.PedidoRepositoryPortaSaida;

import java.awt.print.Pageable;
import java.util.List;

public class BuscaPedidosService implements BuscaPedidosPortaEntrada {

    private final PedidoRepositoryPortaSaida pedidoRepositoryPortaSaida;

    public BuscaPedidosService(PedidoRepositoryPortaSaida pedidoRepositoryPortaSaida) {
        this.pedidoRepositoryPortaSaida = pedidoRepositoryPortaSaida;
    }


    @Override
    public List<PedidoDto> buscaPedidos(Pageable page) {
        return null;
    }
}
