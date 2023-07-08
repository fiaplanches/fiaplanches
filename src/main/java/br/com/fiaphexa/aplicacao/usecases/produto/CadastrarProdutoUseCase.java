package br.com.fiaphexa.aplicacao.usecases.produto;

import br.com.fiaphexa.dominio.dtos.produto.ProdutoDto;
import br.com.fiaphexa.dominio.portas.entrada.produtos.CadastraProdutoPortaEntrada;
import br.com.fiaphexa.dominio.portas.saida.produto.ProdutoRepositoryPortaSaida;

public class CadastrarProdutoUseCase implements CadastraProdutoPortaEntrada {

    private final ProdutoRepositoryPortaSaida produtoRepositoryPortaSaida;

    public CadastrarProdutoUseCase(ProdutoRepositoryPortaSaida produtoRepositoryPortaSaida) {
        this.produtoRepositoryPortaSaida = produtoRepositoryPortaSaida;
    }

    @Override
    public ProdutoDto cadastrarProduto(ProdutoDto produtoDTO) {
        return produtoRepositoryPortaSaida.salvaProduto(produtoDTO);
    }
}
