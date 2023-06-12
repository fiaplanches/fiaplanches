package br.com.fiaplanches.controller;

import br.com.fiaplanches.model.Produto;
import br.com.fiaplanches.records.*;
import br.com.fiaplanches.records.RemoveProduto;
import br.com.fiaplanches.repository.ProdutoRepository;
import br.com.fiaplanches.records.CadastrarProduto;
import br.com.fiaplanches.records.RetornaProduto;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarProduto(@RequestBody @Validated CadastrarProduto cadastrarProduto, UriComponentsBuilder uriBuilder){
        var produto = new Produto(cadastrarProduto);
        produtoRepository.save(produto);

        var uri = uriBuilder.path("/produtos/{nomeProduto}").buildAndExpand(produto.getNomeProduto()).toUri();
        var retornoProduto = new RetornaProduto(produto.getId(), produto.getNomeProduto(), produto.getPreco(), produto.getCategoria());

        return ResponseEntity.created(uri).body(retornoProduto);
    }

    @GetMapping("/{nomeProduto}")
    public ResponseEntity buscaProdutoPorNome(@PathVariable String nomeProduto){
        var produto = produtoRepository.findByNomeProduto(nomeProduto);
        var retornoProduto = new RetornaProduto(produto.getId(), produto.getNomeProduto(), produto.getPreco(), produto.getCategoria());
        return ResponseEntity.ok(retornoProduto);
    }

    @GetMapping("/lista")
    public ResponseEntity<Page<RetornaProduto>> buscaListaProdutos(@PageableDefault(size = 20) Pageable page){
        var listaProduto = produtoRepository.findAll(page).map(produto -> new RetornaProduto(produto.getId(), produto.getNomeProduto(), produto.getPreco(), produto.getCategoria()));
        return ResponseEntity.ok(listaProduto);
    }

    @Transactional
    @PutMapping
    public ResponseEntity updateDataDoctor(@RequestBody CadastrarProduto cadastrarProduto) {
        var buscaProduto = produtoRepository.findByNomeProduto(cadastrarProduto.nomeProduto());
        var novoProduto = new Produto(cadastrarProduto);
        buscaProduto.atualizaProduto(novoProduto);
        var retornoProduto = new RetornaProduto(buscaProduto.getId(), novoProduto.getNomeProduto(), novoProduto.getPreco(), novoProduto.getCategoria());

        return ResponseEntity.ok(retornoProduto);
    }

    @Transactional
    @DeleteMapping
    public ResponseEntity removeProduto(@RequestBody RemoveProduto removeProduto) {
        var produto = produtoRepository.findByNomeProduto(removeProduto.nomeProduto());
        produtoRepository.deleteById(produto.getId());
        return ResponseEntity.ok("Produto excluido com sucesso!");
    }

}
