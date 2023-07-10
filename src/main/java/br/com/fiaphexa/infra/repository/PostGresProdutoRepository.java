package br.com.fiaphexa.infra.repository;

import br.com.fiaphexa.dominio.enuns.Categoria;
import br.com.fiaphexa.infra.entity.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostGresProdutoRepository extends JpaRepository<ProdutoEntity, Long> {

    List<ProdutoEntity> findByCategoria(Categoria categoria);
}
