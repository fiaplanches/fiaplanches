package br.com.fiaphexa.infra.adapter.pedido;

import br.com.fiaphexa.dominio.dtos.pedido.PedidoDto;
import br.com.fiaphexa.dominio.dtos.produto.ProdutoDto;
import br.com.fiaphexa.dominio.portas.saida.pedido.PedidoRepositoryPortaSaida;
import br.com.fiaphexa.infra.repository.PostGresClienteRepository;
import br.com.fiaphexa.infra.repository.PostGresPedidoRepository;
import br.com.fiaphexa.infra.repository.PostGresProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class PedidoRepositoryAdapter implements PedidoRepositoryPortaSaida {

    private final PostGresPedidoRepository postGresPedidoRepository;
    private final PostGresClienteRepository postGresClienteRepository;
    private final PostGresProdutoRepository postGresProdutoRepository;

    public PedidoRepositoryAdapter(PostGresPedidoRepository postGresPedidoRepository, PostGresClienteRepository postGresClienteRepository, PostGresProdutoRepository postGresProdutoRepository) {
        this.postGresPedidoRepository = postGresPedidoRepository;
        this.postGresClienteRepository = postGresClienteRepository;
        this.postGresProdutoRepository = postGresProdutoRepository;
    }

    @Override
    public PedidoDto criaPedido(PedidoDto pedidoDto) {
        var cliente = postGresClienteRepository.findByCpf(pedidoDto.clienteDto().cpf())
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));
        List<Long> idProdutos = pedidoDto.produtosDtos().stream().map(ProdutoDto::id).toList();
        var listaProdutos = postGresProdutoRepository.findAllById(idProdutos);
        var pagaPedido = pagaPedido(new PedidoDto()) //Continuar daqui
    }

    private PedidoDto pagaPedido(PedidoDto pedidoDto){
        BigDecimal valorTotal =
    }

    @Override
    public Page<PedidoDto> buscaPedidosCliente(String cpf) {
        return null;
    }
}