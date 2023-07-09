package br.com.fiaphexa.aplicacao.controllers.produto;

import br.com.fiaphexa.aplicacao.controllers.produto.request.ProdutoRequestDto;
import br.com.fiaphexa.aplicacao.controllers.produto.request.AtualizaProdutoDto;
import br.com.fiaphexa.aplicacao.controllers.produto.response.RetornaProdutoDto;
import br.com.fiaphexa.dominio.enuns.Categoria;
import br.com.fiaphexa.dominio.portas.entrada.produtos.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("produtos")
public class ProdutoControllerAdapter {

    private final CadastraProdutoPortaEntrada cadastraProdutoPortaEntrada;

    private final AtualizaProdutoPortaEntrada atualizaProdutoPortaEntrada;

    private final ProcuraProdutoPorCategoriaPortaEntrada procuraProdutoPorCategoriaPortaEntrada;

    private final RemoveProdutoPortaEntrada removeProdutoPortaEntrada;

    public ProdutoControllerAdapter(
            CadastraProdutoPortaEntrada cadastraProdutoPortaEntrada,
            AtualizaProdutoPortaEntrada atualizaProdutoPortaEntrada,
            ProcuraProdutoPorCategoriaPortaEntrada procuraProdutoPorCategoriaPortaEntrada,
            RemoveProdutoPortaEntrada removeProdutoPortaEntrada) {
        this.cadastraProdutoPortaEntrada = cadastraProdutoPortaEntrada;
        this.atualizaProdutoPortaEntrada = atualizaProdutoPortaEntrada;
        this.procuraProdutoPorCategoriaPortaEntrada = procuraProdutoPorCategoriaPortaEntrada;
        this.removeProdutoPortaEntrada = removeProdutoPortaEntrada;
    }

    @GetMapping("/{categoria}")
    public List<RetornaProdutoDto> buscaPorCategoria(@PathVariable Categoria categoria) {
        var produtos = procuraProdutoPorCategoriaPortaEntrada.procura(categoria);
        return produtos.stream().map(RetornaProdutoDto::new).toList();
    }

    @PostMapping
    public RetornaProdutoDto cadastrarProduto(@RequestBody @Valid ProdutoRequestDto cadastraProdutoRequest, UriComponentsBuilder uriBuilder) {
        var produto = cadastraProdutoPortaEntrada.cadastrarProduto(cadastraProdutoRequest.toProduto());
        var retornoProduto = new RetornaProdutoDto(produto);
        var uri = uriBuilder.path("/produtos/{nomeProduto}").buildAndExpand((retornoProduto)).toUri();
        return retornoProduto;
    }

    @PutMapping
    public RetornaProdutoDto atualizarProduto(@RequestBody @Valid AtualizaProdutoDto atualizaProdutoDto) {
        return new RetornaProdutoDto(atualizaProdutoPortaEntrada.atualizaProduto(atualizaProdutoDto.toProdutoDto()));
    }

    @DeleteMapping("/{id}")
    public String removeProduto(@PathVariable @Valid Long id) {
        removeProdutoPortaEntrada.remove(id);
        return "Produto excluido com sucesso";
    }
}
