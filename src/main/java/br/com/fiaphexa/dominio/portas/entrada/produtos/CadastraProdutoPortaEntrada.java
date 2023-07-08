package br.com.fiaphexa.dominio.portas.entrada.produtos;

import br.com.fiaphexa.dominio.model.Produto;

public interface CadastraProdutoPortaEntrada {

    Produto cadastrarProduto(Produto produto);
}
