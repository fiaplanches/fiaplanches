package br.com.fiaphexa.infra.bean;

import br.com.fiaphexa.aplicacao.services.cliente.CadastraClienteService;
import br.com.fiaphexa.dominio.portas.entrada.clientes.CadastraClientePortaEntrada;
import br.com.fiaphexa.dominio.portas.entrada.produtos.*;
import br.com.fiaphexa.dominio.portas.saida.cliente.ClienteRepositoryPortaSaida;
import br.com.fiaphexa.dominio.portas.saida.produto.ProdutoRepositoryPortaSaida;
import br.com.fiaphexa.aplicacao.services.produto.AtualizaProdutoService;
import br.com.fiaphexa.aplicacao.services.produto.CadastrarProdutoService;
import br.com.fiaphexa.aplicacao.services.produto.ProcuraProdutoPorCategoriaService;
import br.com.fiaphexa.aplicacao.services.produto.RemoveProdutoService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguracao {

    @Bean
    CadastraProdutoPortaEntrada cadastraProdutoPortaEntrada(ProdutoRepositoryPortaSaida produtoRepositoryPort){
        return new CadastrarProdutoService(produtoRepositoryPort);
    }

    @Bean
    AtualizaProdutoPortaEntrada atualizaProdutoPortaEntrada(ProdutoRepositoryPortaSaida produtoRepositoryPort){
        return new AtualizaProdutoService(produtoRepositoryPort);
    }

    @Bean
    ProcuraProdutoPorCategoriaPortaEntrada procuraProdutoPorCategoriaPortaEntrada(ProdutoRepositoryPortaSaida produtoRepositoryPort){
        return new ProcuraProdutoPorCategoriaService(produtoRepositoryPort);
    }

    @Bean
    RemoveProdutoPortaEntrada removeProdutoPortaEntrada(ProdutoRepositoryPortaSaida produtoRepositoryPort){
        return new RemoveProdutoService(produtoRepositoryPort);
    }

    @Bean
    CadastraClientePortaEntrada cadastraClientePortaEntrada(ClienteRepositoryPortaSaida clienteRepositoryPortaSaida){
        return new CadastraClienteService(clienteRepositoryPortaSaida);
    }

}
