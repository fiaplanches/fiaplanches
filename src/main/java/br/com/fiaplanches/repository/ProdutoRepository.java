package br.com.fiaplanches.repository;

import br.com.fiaplanches.enuns.Categoria;
import br.com.fiaplanches.model.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    public Produto findByNomeProduto(String nomeProduto);

    public Page<Produto> findByCategoria(Categoria categoria,
                                         Pageable pageable);
}
