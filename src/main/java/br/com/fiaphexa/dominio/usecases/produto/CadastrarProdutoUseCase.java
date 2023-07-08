package br.com.fiaphexa.dominio.usecases.produto;

import br.com.fiaphexa.dominio.model.Produto;
import br.com.fiaphexa.dominio.portas.entrada.produtos.CadastraProdutoPortaEntrada;
import br.com.fiaphexa.dominio.portas.saida.produto.ProdutoRepositoryPortaSaida;

public class CadastrarProdutoUseCase implements CadastraProdutoPortaEntrada {

    private final ProdutoRepositoryPortaSaida produtoRepositoryPortaSaida;

    public CadastrarProdutoUseCase(ProdutoRepositoryPortaSaida produtoRepositoryPortaSaida) {
        this.produtoRepositoryPortaSaida = produtoRepositoryPortaSaida;
    }

    @Override
    public Produto cadastrarProduto(Produto produto) {
        return produtoRepositoryPortaSaida.salvaProduto(produto);
    }
}
