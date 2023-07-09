package br.com.fiaphexa.infra.adapter.pedido;

import br.com.fiaphexa.dominio.dtos.pedido.PedidoDto;
import br.com.fiaphexa.dominio.portas.saida.pedido.PedidoRepositoryPortaSaida;
import br.com.fiaphexa.infra.repository.PostGresClienteRepository;
import br.com.fiaphexa.infra.repository.PostGresPedidoRepository;
import br.com.fiaphexa.infra.repository.PostGresProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

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
        var cliente = postGresClienteRepository.findByCpf(pedidoDto.cpf())
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));
        List<Long> idProdutos = pedidoDto.produtosDtos();//Continuar daqui
    }

    @Override
    public Page<PedidoDto> buscaPedidos(String cpf) {
        return null;
    }
}