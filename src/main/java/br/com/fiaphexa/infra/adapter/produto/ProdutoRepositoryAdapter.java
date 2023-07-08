package br.com.fiaphexa.infra.adapter.produto;

import br.com.fiaphexa.dominio.dtos.produto.ProdutoDto;
import br.com.fiaphexa.dominio.enuns.Categoria;
import br.com.fiaphexa.dominio.portas.saida.produto.ProdutoRepositoryPortaSaida;
import br.com.fiaphexa.infra.entity.ProdutoEntity;
import br.com.fiaphexa.infra.repository.PostGresProdutoRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ProdutoRepositoryAdapter implements ProdutoRepositoryPortaSaida {

    private final PostGresProdutoRepository springProdutoRepository;

    public ProdutoRepositoryAdapter(PostGresProdutoRepository springProdutoRepository){
        this.springProdutoRepository = springProdutoRepository;
    }

    @Override
    public List<ProdutoDto> procuraProdutoPorCategoria(Categoria categoria) {
        List<ProdutoEntity> produtoEntity = this.springProdutoRepository.findByCategoria(categoria);
        return produtoEntity.stream().map(ProdutoEntity::toProdutoDto).toList();
    }

    @Override
    public ProdutoDto salvaProduto(ProdutoDto produtoDTO){
        ProdutoEntity produtoEntity = new ProdutoEntity(produtoDTO);
        var produtoEntitySave = this.springProdutoRepository.save(produtoEntity);
        return produtoEntitySave.toProdutoDto();
    }

    @Override
    public void removeProduto(ProdutoDto produtoDTO) {
        var produtoEntity = new ProdutoEntity(produtoDTO);
        springProdutoRepository.delete(produtoEntity);
    }

    @Override
    public Optional<ProdutoDto> procuraProdutoPorId(Long id) {
        var produtoEntity = springProdutoRepository.findById(id);
        return produtoEntity.map(ProdutoEntity::toProdutoDto);
    }
}
