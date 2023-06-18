package br.com.fiaplanches.controller;

import br.com.fiaplanches.enuns.Categoria;
import br.com.fiaplanches.model.Produto;
import br.com.fiaplanches.records.*;
import br.com.fiaplanches.records.RemoveProduto;
import br.com.fiaplanches.repository.ProdutoRepository;
import br.com.fiaplanches.records.CadastrarProduto;
import br.com.fiaplanches.records.RetornaProduto;
import br.com.fiaplanches.service.ProdutoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<RetornaProduto> cadastrarProduto(@RequestBody @Valid CadastrarProduto cadastrarProduto, UriComponentsBuilder uriBuilder){
        var retornoProduto = produtoService.cadastrarProduto(cadastrarProduto);
        var uri = uriBuilder.path("/produtos/{nomeProduto}").buildAndExpand((retornoProduto)).toUri();
        return ResponseEntity.created(uri).body(retornoProduto);
    }

    @GetMapping("/{categoria}")
    public ResponseEntity<Page<RetornaProduto>> buscaPorCategoria(@PathVariable Categoria categoria, @PageableDefault(size = 20) Pageable page){
        return ResponseEntity.ok(produtoService.buscaPorCategoria(categoria, page));
    }

    @GetMapping
    public ResponseEntity<Page<RetornaProduto>> buscaListaProdutos(@PageableDefault(size = 20) Pageable page){
        return ResponseEntity.ok(produtoService.buscaListaProdutos(page));
    }

    @PutMapping
    public ResponseEntity<RetornaProduto> updateProduto(@RequestBody RetornaProduto cadastrarProduto) {
        return ResponseEntity.ok(produtoService.updateProduto(cadastrarProduto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeProduto(@PathVariable Long id) {
        produtoService.removeProduto(id);
        return ResponseEntity.ok("Produto excluido com sucesso!");
    }

}
