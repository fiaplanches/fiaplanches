package br.com.fiaphexa.infra.adapter.pedido;

import br.com.fiaphexa.dominio.dtos.PageInfoDto;
import br.com.fiaphexa.dominio.dtos.pedido.PedidoDto;
import br.com.fiaphexa.dominio.portas.saida.pedido.PedidoPagamentoPortaSaida;
import br.com.fiaphexa.dominio.portas.saida.pedido.PedidoRepositoryPortaSaida;
import br.com.fiaphexa.infra.entity.PedidoEntity;
import br.com.fiaphexa.infra.repository.PostGresClienteRepository;
import br.com.fiaphexa.infra.repository.PostGresPedidoRepository;
import br.com.fiaphexa.infra.repository.PostGresProdutoRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PedidoRepositoryAdapter implements PedidoRepositoryPortaSaida {

    private final PostGresPedidoRepository postGresPedidoRepository;

    public PedidoRepositoryAdapter(PostGresPedidoRepository postGresPedidoRepository) {
        this.postGresPedidoRepository = postGresPedidoRepository;
    }

    @Override
    public PedidoDto salvaPedido(PedidoDto pedidoDto) {
        var pedidoEntity = postGresPedidoRepository.save(new PedidoEntity(pedidoDto));
        return pedidoEntity.toPedidoDto();
    }

    @Override
    public List<PedidoDto> buscaPedidosCliente(String cpf, PageInfoDto page) {
        Pageable pageable = PageRequest.of(page.getPageNumber(), page.getPageSize());
        return postGresPedidoRepository.findByCpfCliente(cpf, pageable).stream()
                .map(PedidoEntity::toPedidoDto)
                .toList();
    }

    @Override
    public List<PedidoDto> buscaPedidos(PageInfoDto page) {
        Pageable pageable = PageRequest.of(page.getPageNumber(), page.getPageSize());
        return postGresPedidoRepository.findAll(pageable).stream().map(PedidoEntity::toPedidoDto).toList();
    }
}