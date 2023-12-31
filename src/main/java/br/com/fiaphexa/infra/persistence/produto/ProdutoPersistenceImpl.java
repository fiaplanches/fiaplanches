package br.com.fiaphexa.infra.persistence.produto;

import br.com.fiaphexa.aplicacao.dtos.produto.ProdutoDto;
import br.com.fiaphexa.dominio.enuns.Categoria;
import br.com.fiaphexa.aplicacao.repositorios.produto.ProdutoRepositoryService;
import br.com.fiaphexa.infra.entity.ProdutoEntity;
import br.com.fiaphexa.infra.repository.PostGresProdutoRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ProdutoPersistenceImpl implements ProdutoRepositoryService {

    private final PostGresProdutoRepository postGresProdutoRepository;

    public ProdutoPersistenceImpl(PostGresProdutoRepository postGresProdutoRepository){
        this.postGresProdutoRepository = postGresProdutoRepository;
    }

    @Override
    public List<ProdutoDto> procuraProdutoPorCategoria(Categoria categoria) {
        List<ProdutoEntity> produtoEntity = this.postGresProdutoRepository.findByCategoria(categoria);
        return produtoEntity.stream().map(ProdutoEntity::toProdutoDto).toList();
    }

    @Override
    public ProdutoDto salvaProduto(ProdutoDto produtoDTO){
        ProdutoEntity produtoEntity = new ProdutoEntity(produtoDTO);
        var produtoEntitySave = this.postGresProdutoRepository.save(produtoEntity);
        return produtoEntitySave.toProdutoDto();
    }

    @Override
    public void removeProduto(ProdutoDto produtoDTO) {
        var produtoEntity = new ProdutoEntity(produtoDTO);
        postGresProdutoRepository.delete(produtoEntity);
    }

    @Override
    public Optional<ProdutoDto> procuraProdutoPorId(Long id) {
        var produtoEntity = postGresProdutoRepository.findById(id);
        return produtoEntity.map(ProdutoEntity::toProdutoDto);
    }

    @Override
    public List<ProdutoDto> procuraProdutosPorId(List<Long> id) {
        var produtoEntitys = postGresProdutoRepository.findAllById(id);
        return produtoEntitys.stream().map(ProdutoEntity::toProdutoDto).toList();
    }
}
