package br.com.fiaphexa.aplicacao.casosdeuso.produto;

import br.com.fiaphexa.aplicacao.casosdeuso.abstracoes.produtos.AtualizaProdutoCasoDeUso;
import br.com.fiaphexa.aplicacao.dtos.produto.ProdutoDto;
import br.com.fiaphexa.aplicacao.repositorios.produto.ProdutoRepositoryService;
import jakarta.persistence.EntityNotFoundException;

public class AtualizaProdutoCasoDeUsoImpl implements AtualizaProdutoCasoDeUso {

    private final ProdutoRepositoryService produtoRepositoryService;

    public AtualizaProdutoCasoDeUsoImpl(ProdutoRepositoryService produtoRepositoryService) {
        this.produtoRepositoryService = produtoRepositoryService;
    }

    @Override
    public ProdutoDto atualizaProduto(ProdutoDto novoProdutoDTO) {
        var produtoDTO = produtoRepositoryService.procuraProdutoPorId(novoProdutoDTO.id()).orElseThrow(
                () -> new EntityNotFoundException("Produto não encontrado")
        );
        var produto = produtoDTO.toProduto();
        var produtoAtualizado = produto.atualizaProduto(novoProdutoDTO.toProduto());
        return produtoRepositoryService.salvaProduto(ProdutoDto.toProdutoDto(produtoAtualizado));
    }
}
