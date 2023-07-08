package br.com.fiaphexa.dominio.portas.saida.produto;

import br.com.fiaphexa.aplicacao.controllers.dtos.RetornaProdutoDto;
import br.com.fiaphexa.dominio.enuns.Categoria;
import br.com.fiaphexa.dominio.model.Produto;

import java.util.List;
import java.util.Optional;

public interface ProdutoRepositoryPortaSaida {

    List<RetornaProdutoDto> retornaListaProduto();

    List<Produto> procuraProdutoPorCategoria(Categoria categoria);

    Produto salvaProduto(Produto produto);

    void removeProduto(Produto produto);

//    Produto atualizaProduto(Produto produto);

    Optional<Produto> procuraProdutoPorId(Long id);
}
