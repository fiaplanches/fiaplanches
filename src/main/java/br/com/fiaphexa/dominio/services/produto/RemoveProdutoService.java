package br.com.fiaphexa.dominio.services.produto;

import br.com.fiaphexa.dominio.portas.entrada.produtos.RemoveProdutoPortaEntrada;
import br.com.fiaphexa.dominio.portas.saida.produto.ProdutoRepositoryPortaSaida;
import jakarta.persistence.EntityNotFoundException;

public class RemoveProdutoService implements RemoveProdutoPortaEntrada {

    private final ProdutoRepositoryPortaSaida produtoRepositoryPortaSaida;

    public RemoveProdutoService(ProdutoRepositoryPortaSaida produtoRepositoryPortaSaida) {
        this.produtoRepositoryPortaSaida = produtoRepositoryPortaSaida;
    }

    @Override
    public void remove(Long id) {
        var produtoDTO = produtoRepositoryPortaSaida.procuraProdutoPorId(id).orElseThrow(
                () -> new EntityNotFoundException("Produto não encontrado")
        );
        produtoRepositoryPortaSaida.removeProduto(produtoDTO);
    }
}
