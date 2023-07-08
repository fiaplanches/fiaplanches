package br.com.fiaphexa.infra.adapter;

import br.com.fiaphexa.aplicacao.controllers.dtos.RetornaProdutoDto;
import br.com.fiaphexa.dominio.enuns.Categoria;
import br.com.fiaphexa.dominio.model.Produto;
import br.com.fiaphexa.dominio.portas.saida.produto.ProdutoRepositoryPortaSaida;
import br.com.fiaphexa.infra.entity.ProdutoEntity;
import br.com.fiaphexa.infra.repository.SpringProdutoRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ProdutoRepositoryAdapter implements ProdutoRepositoryPortaSaida {

    private final SpringProdutoRepository springProdutoRepository;

    public ProdutoRepositoryAdapter(SpringProdutoRepository springProdutoRepository){
        this.springProdutoRepository = springProdutoRepository;
    }

    @Override
    public List<RetornaProdutoDto> retornaListaProduto() {
        return null;
    }

    @Override
    public List<Produto> procuraProdutoPorCategoria(Categoria categoria) {
        List<ProdutoEntity> produtoEntity = this.springProdutoRepository.findByCategoria(categoria);
        return produtoEntity.stream().map(ProdutoEntity::toProduto).toList();
    }

    @Override
    public Produto salvaProduto(Produto produto){
        ProdutoEntity produtoEntity = new ProdutoEntity(produto);
        var produtoEntitySave = this.springProdutoRepository.save(produtoEntity);
        return produtoEntitySave.toProduto();
    }

    @Override
    public void removeProduto(Produto produto) {
        var produtoEntity = new ProdutoEntity(produto);
        springProdutoRepository.delete(produtoEntity);
    }

//    @Override
//    public RetornaProdutoDto atualizaProduto(AtualizaProdutoDto atualizaProdutoDto) {
//        var produtoEntity = springProdutoRepository.findById(atualizaProdutoDto.id())
//                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));
//        BeanUtils.copyProperties(atualizaProdutoDto, produtoEntity, "id");
//        return new RetornaProdutoDto(springProdutoRepository.save(produtoEntity));
//    }

    @Override
    public Optional<Produto> procuraProdutoPorId(Long id) {
        var produtoEntity = springProdutoRepository.findById(id);
        return produtoEntity.map(ProdutoEntity::toProduto);
    }
}
