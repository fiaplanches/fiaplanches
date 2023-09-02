package br.com.fiaphexa.aplicacao.casosdeuso.produto;

import br.com.fiaphexa.aplicacao.dtos.produto.ProdutoDto;
import br.com.fiaphexa.aplicacao.casosdeuso.abstracoes.produtos.CadastraProdutoCasoDeUso;
import br.com.fiaphexa.aplicacao.repositorios.produto.ProdutoRepositoryService;

public class CadastrarProdutoCasoDeUsoImpl implements CadastraProdutoCasoDeUso {

    private final ProdutoRepositoryService produtoRepositoryService;

    public CadastrarProdutoCasoDeUsoImpl(ProdutoRepositoryService produtoRepositoryService) {
        this.produtoRepositoryService = produtoRepositoryService;
    }

    @Override
    public ProdutoDto cadastrarProduto(ProdutoDto produtoDTO) {
        return produtoRepositoryService.salvaProduto(produtoDTO);
    }
}
