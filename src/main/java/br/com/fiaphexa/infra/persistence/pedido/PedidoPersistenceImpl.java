package br.com.fiaphexa.infra.persistence.pedido;

import br.com.fiaphexa.aplicacao.dtos.PageInfoDto;
import br.com.fiaphexa.aplicacao.dtos.pedido.PedidoDto;
import br.com.fiaphexa.aplicacao.repositorios.pedido.PedidoRepositoryService;
import br.com.fiaphexa.infra.entity.ClienteEntity;
import br.com.fiaphexa.infra.entity.PedidoEntity;
import br.com.fiaphexa.infra.repository.PostGresPedidoRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PedidoPersistenceImpl implements PedidoRepositoryService {

    private final PostGresPedidoRepository postGresPedidoRepository;

    public PedidoPersistenceImpl(PostGresPedidoRepository postGresPedidoRepository) {
        this.postGresPedidoRepository = postGresPedidoRepository;
    }

    @Override
    public PedidoDto salvaPedido(PedidoDto pedidoDto) {
        var pedidoEntity = postGresPedidoRepository.save(new PedidoEntity(pedidoDto));
        return pedidoEntity.toPedidoDto();
    }

    @Override
    public Optional<PedidoDto> buscaPedidoPorId(Long idPedido) {
        return postGresPedidoRepository.findByIdPedido(idPedido).map(PedidoEntity::toPedidoDto);
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