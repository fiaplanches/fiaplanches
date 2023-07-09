package br.com.fiaphexa.aplicacao.services.produto;

import br.com.fiaphexa.dominio.dtos.produto.ProdutoDto;
import br.com.fiaphexa.dominio.enuns.Categoria;
import br.com.fiaphexa.dominio.portas.entrada.produtos.ProcuraProdutoPorCategoriaPortaEntrada;
import br.com.fiaphexa.dominio.portas.saida.produto.ProdutoRepositoryPortaSaida;

import java.util.List;

public class ProcuraProdutoPorCategoriaService implements ProcuraProdutoPorCategoriaPortaEntrada {

    private final ProdutoRepositoryPortaSaida produtoRepositoryPortaSaida;
    public ProcuraProdutoPorCategoriaService(ProdutoRepositoryPortaSaida produtoRepositoryPort) {
        this.produtoRepositoryPortaSaida = produtoRepositoryPort;
    }

    @Override
    public List<ProdutoDto> procura(Categoria categoria) {
        //Criar valueObject para validar se a Categoria e null ou vazia
        return produtoRepositoryPortaSaida.procuraProdutoPorCategoria(categoria);
    }
}
