package br.com.fiaphexa.dominio.usecases.produto;

import br.com.fiaphexa.dominio.portas.entrada.produtos.RemoveProdutoPortaEntrada;
import br.com.fiaphexa.dominio.portas.saida.produto.ProdutoRepositoryPortaSaida;
import jakarta.persistence.EntityNotFoundException;

public class RemoveProdutoUseCase implements RemoveProdutoPortaEntrada {

    private final ProdutoRepositoryPortaSaida produtoRepositoryPortaSaida;

    public RemoveProdutoUseCase(ProdutoRepositoryPortaSaida produtoRepositoryPortaSaida) {
        this.produtoRepositoryPortaSaida = produtoRepositoryPortaSaida;
    }

    @Override
    public void remove(Long id) {
        var produto = produtoRepositoryPortaSaida.procuraProdutoPorId(id).orElseThrow(
                () -> new EntityNotFoundException("Produto não encontrado")
        );
        produtoRepositoryPortaSaida.removeProduto(produto);
    }
}
