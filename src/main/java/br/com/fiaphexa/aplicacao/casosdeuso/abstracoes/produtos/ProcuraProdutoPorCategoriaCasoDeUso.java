package br.com.fiaphexa.aplicacao.casosdeuso.abstracoes.produtos;

import br.com.fiaphexa.aplicacao.dtos.produto.ProdutoDto;
import br.com.fiaphexa.dominio.enuns.Categoria;

import java.util.List;

public interface ProcuraProdutoPorCategoriaCasoDeUso {

    List<ProdutoDto> procura(Categoria categoria);
}
