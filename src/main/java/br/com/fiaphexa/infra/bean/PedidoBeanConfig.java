package br.com.fiaphexa.infra.bean;

import br.com.fiaphexa.aplicacao.casosdeuso.abstracoes.pedidos.*;
import br.com.fiaphexa.aplicacao.casosdeuso.pedido.*;
import br.com.fiaphexa.aplicacao.repositorios.cliente.ClienteRepositoryService;
import br.com.fiaphexa.aplicacao.repositorios.pedido.PedidoPagamentoRepositoryService;
import br.com.fiaphexa.aplicacao.repositorios.pedido.PedidoRepositoryService;
import br.com.fiaphexa.aplicacao.repositorios.produto.ProdutoRepositoryService;
import br.com.fiaphexa.infra.persistence.pedido.PedidoPagamentoPersistenceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PedidoBeanConfig {

    @Bean
    public BuscaPedidosCasoDeUso buscaPedidosPortaEntrada(PedidoRepositoryService pedidoRepositoryService){
        return new BuscaPedidosCasoDeUsoImpl(pedidoRepositoryService);
    }

    @Bean
    public BuscaPedidosClienteCasoDeUso buscaPedidosClientePortaEntrada(PedidoRepositoryService pedidoRepositoryService){
        return new BuscaPedidosClienteCasoDeUsoImpl(pedidoRepositoryService);
    }

    @Bean
    public CriaPedidoCasoDeUso criaPedidoPortaEntrada(
            PedidoRepositoryService pedidoRepositoryService,
            ClienteRepositoryService clienteRepositoryService,
            ProdutoRepositoryService produtoRepositoryService,
            PedidoPagamentoRepositoryService pedidoPagamentoService
    ){
        return new CriarPedidoCasoDeUsoImpl(
                pedidoRepositoryService, clienteRepositoryService, produtoRepositoryService,
                pedidoPagamentoService);
    }

    @Bean
    public AdicionaNoCarrinhoCasoDeUso adicionaNoCarrinhoCasoDeUso(
            PedidoRepositoryService pedidoRepositoryService,
            ClienteRepositoryService clienteRepositoryService,
            ProdutoRepositoryService produtoRepositoryService
    ){
        return new AdicionaNoCarrinhoCasoDeUsoImpl(
                pedidoRepositoryService, clienteRepositoryService, produtoRepositoryService);
    }

    @Bean
    public ConsultaStatusPagamentoCasoDeUso consultaStatusPagamentoCasoDeUso(
            PedidoRepositoryService pedidoRepositoryService,
            ClienteRepositoryService clienteRepositoryService
    ){
        return new ConsultaStatusPagamentoCasoDeUsoImpl(
                pedidoRepositoryService, clienteRepositoryService);
    }

    @Bean
    public PedidoPagamentoRepositoryService pedidoRepositoryPortaSaida(){
        return new PedidoPagamentoPersistenceImpl();
    }
}
