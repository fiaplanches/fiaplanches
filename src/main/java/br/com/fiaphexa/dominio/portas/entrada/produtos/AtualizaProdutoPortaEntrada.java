package br.com.fiaphexa.dominio.portas.entrada.produtos;

import br.com.fiaphexa.dominio.dtos.produto.ProdutoDto;

public interface AtualizaProdutoPortaEntrada {

    ProdutoDto atualizaProduto(ProdutoDto produtoDTO);
}
