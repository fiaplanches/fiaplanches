package br.com.fiaphexa.dominio.portas.entrada.produtos;

import br.com.fiaphexa.dominio.dtos.produto.ProdutoDto;
import br.com.fiaphexa.dominio.enuns.Categoria;

import java.util.List;

public interface ProcuraProdutoPorCategoriaPortaEntrada {

    List<ProdutoDto> procura(Categoria categoria);
}
