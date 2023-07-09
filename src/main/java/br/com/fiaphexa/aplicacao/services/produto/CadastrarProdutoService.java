package br.com.fiaphexa.aplicacao.services.produto;

import br.com.fiaphexa.dominio.dtos.produto.ProdutoDto;
import br.com.fiaphexa.dominio.portas.entrada.produtos.CadastraProdutoPortaEntrada;
import br.com.fiaphexa.dominio.portas.saida.produto.ProdutoRepositoryPortaSaida;

public class CadastrarProdutoService implements CadastraProdutoPortaEntrada {

    private final ProdutoRepositoryPortaSaida produtoRepositoryPortaSaida;

    public CadastrarProdutoService(ProdutoRepositoryPortaSaida produtoRepositoryPortaSaida) {
        this.produtoRepositoryPortaSaida = produtoRepositoryPortaSaida;
    }

    @Override
    public ProdutoDto cadastrarProduto(ProdutoDto produtoDTO) {
        return produtoRepositoryPortaSaida.salvaProduto(produtoDTO);
    }
}
