package br.com.fiaplanches.service;

import br.com.fiaplanches.enuns.Categoria;
import br.com.fiaplanches.model.Produto;
import br.com.fiaplanches.records.CadastrarProduto;
import br.com.fiaplanches.records.RetornaProduto;
import br.com.fiaplanches.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public RetornaProduto cadastrarProduto(CadastrarProduto cadastrarProduto){
        return new RetornaProduto(produtoRepository.save(new Produto(cadastrarProduto)));
    }

    public Page<RetornaProduto> buscaPorCategoria(Categoria categoria, Pageable page) {
        return produtoRepository.findByCategoria(categoria, page).map(RetornaProduto::new);
    }

    public Page<RetornaProduto> buscaListaProdutos(Pageable page) {
        return produtoRepository.findAll(page).map(RetornaProduto::new);
    }

    public RetornaProduto updateProduto(RetornaProduto updateProduto) {
        var produto = produtoRepository.findById(updateProduto.id())
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));
        produto.setPreco(updateProduto.preco());
        produto.setCategoria(updateProduto.categoria());
        produto.setNomeProduto(updateProduto.nomeProduto());
        return new RetornaProduto(produtoRepository.save(produto));
    }

    public void removeProduto(Long id) {
        var produto = produtoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));
        produtoRepository.delete(produto);
    }
}
