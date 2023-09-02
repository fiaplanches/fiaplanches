package br.com.fiaphexa.aplicacao.casosdeuso.produto;

import br.com.fiaphexa.aplicacao.dtos.produto.ProdutoDto;
import br.com.fiaphexa.dominio.enuns.Categoria;
import br.com.fiaphexa.aplicacao.casosdeuso.abstracoes.produtos.ProcuraProdutoPorCategoriaCasoDeUso;
import br.com.fiaphexa.aplicacao.repositorios.produto.ProdutoRepositoryService;

import java.util.List;

public class ProcuraProdutoPorCategoriaCasoDeUsoImpl implements ProcuraProdutoPorCategoriaCasoDeUso {

    private final ProdutoRepositoryService produtoRepositoryService;
    public ProcuraProdutoPorCategoriaCasoDeUsoImpl(ProdutoRepositoryService produtoRepositoryPort) {
        this.produtoRepositoryService = produtoRepositoryPort;
    }

    @Override
    public List<ProdutoDto> procura(Categoria categoria) {
        //Criar valueObject para validar se a Categoria e null ou vazia
        return produtoRepositoryService.procuraProdutoPorCategoria(categoria);
    }
}
