package br.com.fiaphexa.aplicacao.services.produto;

import br.com.fiaphexa.dominio.dtos.produto.ProdutoDto;
import br.com.fiaphexa.dominio.portas.entrada.produtos.AtualizaProdutoPortaEntrada;
import br.com.fiaphexa.dominio.portas.saida.produto.ProdutoRepositoryPortaSaida;
import jakarta.persistence.EntityNotFoundException;

public class AtualizaProdutoService implements AtualizaProdutoPortaEntrada {

    private final ProdutoRepositoryPortaSaida produtoRepositoryPortaSaida;

    public AtualizaProdutoService(ProdutoRepositoryPortaSaida produtoRepositoryPortaSaida) {
        this.produtoRepositoryPortaSaida = produtoRepositoryPortaSaida;
    }

    @Override
    public ProdutoDto atualizaProduto(ProdutoDto novoProdutoDTO) {
        var produtoDTO = produtoRepositoryPortaSaida.procuraProdutoPorId(novoProdutoDTO.id()).orElseThrow(
                () -> new EntityNotFoundException("Produto não encontrado")
        );
        var produto = produtoDTO.toProduto();
        var produtoAtualizado = produto.atualizaProduto(novoProdutoDTO.toProduto());
        return produtoRepositoryPortaSaida.salvaProduto(ProdutoDto.toProdutoDto(produtoAtualizado));
    }
}
