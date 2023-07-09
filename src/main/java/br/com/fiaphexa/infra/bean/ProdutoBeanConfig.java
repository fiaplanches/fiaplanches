package br.com.fiaphexa.infra.bean;

import br.com.fiaphexa.dominio.portas.entrada.produtos.AtualizaProdutoPortaEntrada;
import br.com.fiaphexa.dominio.portas.entrada.produtos.CadastraProdutoPortaEntrada;
import br.com.fiaphexa.dominio.portas.entrada.produtos.ProcuraProdutoPorCategoriaPortaEntrada;
import br.com.fiaphexa.dominio.portas.entrada.produtos.RemoveProdutoPortaEntrada;
import br.com.fiaphexa.dominio.portas.saida.produto.ProdutoRepositoryPortaSaida;
import br.com.fiaphexa.dominio.services.produto.AtualizaProdutoService;
import br.com.fiaphexa.dominio.services.produto.CadastrarProdutoService;
import br.com.fiaphexa.dominio.services.produto.ProcuraProdutoPorCategoriaService;
import br.com.fiaphexa.dominio.services.produto.RemoveProdutoService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProdutoBeanConfig {

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



}
