package br.com.fiaphexa.infra.config;

import br.com.fiaphexa.aplicacao.casosdeuso.abstracoes.produtos.AtualizaProdutoCasoDeUso;
import br.com.fiaphexa.aplicacao.casosdeuso.abstracoes.produtos.CadastraProdutoCasoDeUso;
import br.com.fiaphexa.aplicacao.casosdeuso.abstracoes.produtos.ProcuraProdutoPorCategoriaCasoDeUso;
import br.com.fiaphexa.aplicacao.casosdeuso.abstracoes.produtos.RemoveProdutoCasoDeUso;
import br.com.fiaphexa.aplicacao.repositorios.produto.ProdutoRepositoryService;
import br.com.fiaphexa.aplicacao.casosdeuso.produto.CadastrarProdutoCasoDeUsoImpl;
import br.com.fiaphexa.aplicacao.casosdeuso.produto.AtualizaProdutoCasoDeUsoImpl;
import br.com.fiaphexa.aplicacao.casosdeuso.produto.ProcuraProdutoPorCategoriaCasoDeUsoImpl;
import br.com.fiaphexa.aplicacao.casosdeuso.produto.RemoveProdutoCasoDeUsoImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProdutoBeanConfig {

    @Bean
    CadastraProdutoCasoDeUso cadastraProdutoPortaEntrada(ProdutoRepositoryService produtoRepositoryPort){
        return new CadastrarProdutoCasoDeUsoImpl(produtoRepositoryPort);
    }

    @Bean
    AtualizaProdutoCasoDeUso atualizaProdutoPortaEntrada(ProdutoRepositoryService produtoRepositoryPort){
        return new AtualizaProdutoCasoDeUsoImpl(produtoRepositoryPort);
    }

    @Bean
    ProcuraProdutoPorCategoriaCasoDeUso procuraProdutoPorCategoriaPortaEntrada(ProdutoRepositoryService produtoRepositoryPort){
        return new ProcuraProdutoPorCategoriaCasoDeUsoImpl(produtoRepositoryPort);
    }

    @Bean
    RemoveProdutoCasoDeUso removeProdutoPortaEntrada(ProdutoRepositoryService produtoRepositoryPort){
        return new RemoveProdutoCasoDeUsoImpl(produtoRepositoryPort);
    }



}
