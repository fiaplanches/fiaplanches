package br.com.fiaphexa.dominio.services.pedido;

import br.com.fiaphexa.dominio.dtos.PageInfoDto;
import br.com.fiaphexa.dominio.dtos.pedido.PedidoDto;
import br.com.fiaphexa.dominio.portas.entrada.pedidos.BuscaPedidosClientePortaEntrada;
import br.com.fiaphexa.dominio.portas.saida.pedido.PedidoRepositoryPortaSaida;

import java.util.List;

public class BuscaPedidosClienteService implements BuscaPedidosClientePortaEntrada {

    private final PedidoRepositoryPortaSaida pedidoRepositoryPortaSaida;

    public BuscaPedidosClienteService(PedidoRepositoryPortaSaida pedidoRepositoryPortaSaida) {
        this.pedidoRepositoryPortaSaida = pedidoRepositoryPortaSaida;
    }

    @Override
    public List<PedidoDto> buscaPedidosCliente(String cpf, PageInfoDto page) {
        return pedidoRepositoryPortaSaida.buscaPedidosCliente(cpf, page);
    }
}
