package br.com.fiaphexa.infra.bean;

import br.com.fiaphexa.dominio.portas.entrada.pedidos.BuscaPedidosClientePortaEntrada;
import br.com.fiaphexa.dominio.portas.entrada.pedidos.BuscaPedidosPortaEntrada;
import br.com.fiaphexa.dominio.portas.entrada.pedidos.CriaPedidoPortaEntrada;
import br.com.fiaphexa.dominio.portas.saida.pedido.PedidoPagamentoPortaSaida;
import br.com.fiaphexa.dominio.portas.saida.pedido.PedidoRepositoryPortaSaida;
import br.com.fiaphexa.dominio.services.pedido.BuscaPedidosClienteService;
import br.com.fiaphexa.dominio.services.pedido.BuscaPedidosService;
import br.com.fiaphexa.dominio.services.pedido.CriarPedidosService;
import br.com.fiaphexa.infra.adapter.pedido.PedidoPagamentoAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PedidoBeanConfig {

    @Bean
    public BuscaPedidosPortaEntrada buscaPedidosPortaEntrada(PedidoRepositoryPortaSaida pedidoRepositoryPortaSaida){
        return new BuscaPedidosService(pedidoRepositoryPortaSaida);
    }

    @Bean
    public BuscaPedidosClientePortaEntrada buscaPedidosClientePortaEntrada(PedidoRepositoryPortaSaida pedidoRepositoryPortaSaida){
        return new BuscaPedidosClienteService(pedidoRepositoryPortaSaida);
    }

    @Bean
    public CriaPedidoPortaEntrada criaPedidoPortaEntrada(PedidoRepositoryPortaSaida pedidoRepositoryPortaSaida){
        return new CriarPedidosService(pedidoRepositoryPortaSaida);
    }

    @Bean
    public PedidoPagamentoPortaSaida pedidoRepositoryPortaSaida(){
        return new PedidoPagamentoAdapter();
    }
}
