package br.com.fiaphexa.dominio.portas.saida.produto;

import br.com.fiaphexa.dominio.dtos.produto.ProdutoDto;
import br.com.fiaphexa.dominio.enuns.Categoria;

import java.util.List;
import java.util.Optional;

public interface ProdutoRepositoryPortaSaida {

    List<ProdutoDto> procuraProdutoPorCategoria(Categoria categoria);

    ProdutoDto salvaProduto(ProdutoDto produtoDTO);

    void removeProduto(ProdutoDto produtoDTO);

    Optional<ProdutoDto> procuraProdutoPorId(Long id);

    List<ProdutoDto> procuraProdutosPorId(List<Long> ids);
}
