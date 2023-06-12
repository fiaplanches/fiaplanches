package br.com.fiaplanches.repository;

import br.com.fiaplanches.enuns.Categoria;
import br.com.fiaplanches.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    public Produto findByNomeProduto(String nomeProduto);

    public List<Produto> findByCategoria(Categoria categoria);
}
