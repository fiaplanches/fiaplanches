package br.com.fiaphexa.dominio.portas.entrada.produtos;

import br.com.fiaphexa.dominio.enuns.Categoria;
import br.com.fiaphexa.dominio.model.Produto;

import java.util.List;

public interface ProcuraProdutoPorCategoriaPortaEntrada {

    List<Produto> procura(Categoria categoria);
}
