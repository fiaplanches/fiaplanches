package br.com.fiaphexa.dominio.usecases.produto;

import br.com.fiaphexa.dominio.model.Produto;
import br.com.fiaphexa.dominio.portas.entrada.produtos.AtualizaProdutoPortaEntrada;
import br.com.fiaphexa.dominio.portas.saida.produto.ProdutoRepositoryPortaSaida;
import jakarta.persistence.EntityNotFoundException;

public class AtualizaProdutoUseCase implements AtualizaProdutoPortaEntrada {

    private final ProdutoRepositoryPortaSaida produtoRepositoryPortaSaida;

    public AtualizaProdutoUseCase(ProdutoRepositoryPortaSaida produtoRepositoryPortaSaida) {
        this.produtoRepositoryPortaSaida = produtoRepositoryPortaSaida;
    }

    @Override
    public Produto atualizaProduto(Produto novoProduto) {
        var produto = produtoRepositoryPortaSaida.procuraProdutoPorId(novoProduto.getId()).orElseThrow(
                () -> new EntityNotFoundException("Produto não encontrado")
        );
        var produtoAtualizado = produto.atualizaProduto(novoProduto);
        return produtoRepositoryPortaSaida.salvaProduto(produtoAtualizado);
    }
}
