package br.com.fiaphexa.web.controllers.produto;

import br.com.fiaphexa.aplicacao.casosdeuso.abstracoes.produtos.AtualizaProdutoCasoDeUso;
import br.com.fiaphexa.aplicacao.casosdeuso.abstracoes.produtos.CadastraProdutoCasoDeUso;
import br.com.fiaphexa.aplicacao.casosdeuso.abstracoes.produtos.ProcuraProdutoPorCategoriaCasoDeUso;
import br.com.fiaphexa.aplicacao.casosdeuso.abstracoes.produtos.RemoveProdutoCasoDeUso;
import br.com.fiaphexa.web.controllers.produto.request.ProdutoRequestDto;
import br.com.fiaphexa.web.controllers.produto.request.AtualizaProdutoDto;
import br.com.fiaphexa.web.controllers.produto.response.RetornaProdutoDto;
import br.com.fiaphexa.dominio.enuns.Categoria;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("produtos")
public class ProdutoController {

    private final CadastraProdutoCasoDeUso cadastraProdutoCasoDeUso;

    private final AtualizaProdutoCasoDeUso atualizaProdutoCasoDeUso;

    private final ProcuraProdutoPorCategoriaCasoDeUso procuraProdutoPorCategoriaCasoDeUso;

    private final RemoveProdutoCasoDeUso removeProdutoCasoDeUso;

    public ProdutoController(
            CadastraProdutoCasoDeUso cadastraProdutoCasoDeUso,
            AtualizaProdutoCasoDeUso atualizaProdutoCasoDeUso,
            ProcuraProdutoPorCategoriaCasoDeUso procuraProdutoPorCategoriaCasoDeUso,
            RemoveProdutoCasoDeUso removeProdutoCasoDeUso) {
        this.cadastraProdutoCasoDeUso = cadastraProdutoCasoDeUso;
        this.atualizaProdutoCasoDeUso = atualizaProdutoCasoDeUso;
        this.procuraProdutoPorCategoriaCasoDeUso = procuraProdutoPorCategoriaCasoDeUso;
        this.removeProdutoCasoDeUso = removeProdutoCasoDeUso;
    }

    @GetMapping("/{categoria}")
    public List<RetornaProdutoDto> buscaPorCategoria(@PathVariable Categoria categoria) {
        var produtos = procuraProdutoPorCategoriaCasoDeUso.procura(categoria);
        return produtos.stream().map(RetornaProdutoDto::new).toList();
    }

    @PostMapping
    public RetornaProdutoDto cadastrarProduto(@RequestBody @Valid ProdutoRequestDto cadastraProdutoRequest, UriComponentsBuilder uriBuilder) {
        var produto = cadastraProdutoCasoDeUso.cadastrarProduto(cadastraProdutoRequest.toProduto());
        var retornoProduto = new RetornaProdutoDto(produto);
        var uri = uriBuilder.path("/produtos/{nomeProduto}").buildAndExpand((retornoProduto)).toUri();
        return retornoProduto;
    }

    @PutMapping
    public RetornaProdutoDto atualizarProduto(@RequestBody @Valid AtualizaProdutoDto atualizaProdutoDto) {
        return new RetornaProdutoDto(atualizaProdutoCasoDeUso.atualizaProduto(atualizaProdutoDto.toProdutoDto()));
    }

    @DeleteMapping("/{id}")
    public String removeProduto(@PathVariable @Valid Long id) {
        removeProdutoCasoDeUso.remove(id);
        return "Produto excluido com sucesso";
    }
}
