package br.com.fiaphexa.dominio.usecases.produto;

import br.com.fiaphexa.dominio.enuns.Categoria;
import br.com.fiaphexa.dominio.model.Produto;
import br.com.fiaphexa.dominio.portas.entrada.produtos.ProcuraProdutoPorCategoriaPortaEntrada;
import br.com.fiaphexa.dominio.portas.saida.produto.ProdutoRepositoryPortaSaida;

import java.util.List;

public class ProcuraProdutoPorCategoriaUseCase implements ProcuraProdutoPorCategoriaPortaEntrada {

    private final ProdutoRepositoryPortaSaida produtoRepositoryPortaSaida;
    public ProcuraProdutoPorCategoriaUseCase(ProdutoRepositoryPortaSaida produtoRepositoryPort) {
        this.produtoRepositoryPortaSaida = produtoRepositoryPort;
    }

    @Override
    public List<Produto> procura(Categoria categoria) {
        //Criar valueObject para validar se a Categoria e null ou vazia
        return produtoRepositoryPortaSaida.procuraProdutoPorCategoria(categoria);
    }
}
