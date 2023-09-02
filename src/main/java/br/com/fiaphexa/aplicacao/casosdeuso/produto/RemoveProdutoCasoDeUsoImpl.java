package br.com.fiaphexa.aplicacao.casosdeuso.produto;

import br.com.fiaphexa.aplicacao.casosdeuso.abstracoes.produtos.RemoveProdutoCasoDeUso;
import br.com.fiaphexa.aplicacao.repositorios.produto.ProdutoRepositoryService;
import jakarta.persistence.EntityNotFoundException;

public class RemoveProdutoCasoDeUsoImpl implements RemoveProdutoCasoDeUso {

    private final ProdutoRepositoryService produtoRepositoryService;

    public RemoveProdutoCasoDeUsoImpl(ProdutoRepositoryService produtoRepositoryService) {
        this.produtoRepositoryService = produtoRepositoryService;
    }

    @Override
    public void remove(Long id) {
        var produtoDTO = produtoRepositoryService.procuraProdutoPorId(id).orElseThrow(
                () -> new EntityNotFoundException("Produto não encontrado")
        );
        produtoRepositoryService.removeProduto(produtoDTO);
    }
}
