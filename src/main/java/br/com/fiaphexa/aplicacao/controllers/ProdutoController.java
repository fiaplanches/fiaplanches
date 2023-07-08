package br.com.fiaphexa.aplicacao.controllers;

import br.com.fiaphexa.aplicacao.controllers.request.CadastraProdutoRequest;
import br.com.fiaphexa.aplicacao.controllers.dtos.AtualizaProdutoDto;
import br.com.fiaphexa.aplicacao.controllers.dtos.RetornaProdutoDto;
import br.com.fiaphexa.dominio.enuns.Categoria;
import br.com.fiaphexa.dominio.portas.entrada.produtos.*;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("produtos")
public class ProdutoController {

    private final CadastraProdutoPortaEntrada cadastraProdutoPortaEntrada;

    private final AtualizaProdutoPortaEntrada atualizaProdutoPortaEntrada;

    private final ProcuraProdutoPorCategoriaPortaEntrada procuraProdutoPorCategoriaPortaEntrada;

    private final RemoveProdutoPortaEntrada removeProdutoPortaEntrada;

    public ProdutoController(
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
    public List<RetornaProdutoDto> buscaPorCategoria(@PathVariable @Valid Categoria categoria) {
        var produtos = procuraProdutoPorCategoriaPortaEntrada.procura(categoria);
        return produtos.stream().map(RetornaProdutoDto::new).toList();
    }

    @PostMapping
    public RetornaProdutoDto cadastrarProduto(@RequestBody CadastraProdutoRequest cadastraProdutoRequest, UriComponentsBuilder uriBuilder) {
        var produto = cadastraProdutoPortaEntrada.cadastrarProduto(cadastraProdutoRequest.toProduto());
        var retornoProduto = new RetornaProdutoDto(produto);
        var uri = uriBuilder.path("/produtos/{nomeProduto}").buildAndExpand((retornoProduto)).toUri();
        return retornoProduto;
    }

    @PutMapping
    public RetornaProdutoDto atualizarProduto(@RequestBody AtualizaProdutoDto atualizaProdutoDto) {
        return new RetornaProdutoDto(atualizaProdutoPortaEntrada.atualizaProduto(atualizaProdutoDto.toProduto()));
    }

    @DeleteMapping("/{id}")
    public String removeProduto(@PathVariable @Valid Long id) {
        removeProdutoPortaEntrada.remove(id);
        return "Produto excluido com sucesso";
    }
}
