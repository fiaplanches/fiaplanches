package br.com.fiaphexa.infra.adapter.pedido;

import br.com.fiaphexa.aplicacao.excecoes.erros.PaymentRefusedException;
import br.com.fiaphexa.dominio.dtos.PageInfoDto;
import br.com.fiaphexa.dominio.dtos.pedido.PedidoDto;
import br.com.fiaphexa.dominio.dtos.produto.ProdutoDto;
import br.com.fiaphexa.dominio.enuns.StatusPedido;
import br.com.fiaphexa.dominio.portas.saida.pedido.PedidoPagamentoPortaSaida;
import br.com.fiaphexa.dominio.portas.saida.pedido.PedidoRepositoryPortaSaida;
import br.com.fiaphexa.infra.entity.PedidoEntity;
import br.com.fiaphexa.infra.entity.ProdutoEntity;
import br.com.fiaphexa.infra.repository.PostGresClienteRepository;
import br.com.fiaphexa.infra.repository.PostGresPedidoRepository;
import br.com.fiaphexa.infra.repository.PostGresProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class PedidoRepositoryAdapter implements PedidoRepositoryPortaSaida {

    private final PostGresPedidoRepository postGresPedidoRepository;
    private final PostGresClienteRepository postGresClienteRepository;
    private final PostGresProdutoRepository postGresProdutoRepository;
    private final PedidoPagamentoPortaSaida pedidoPagamentoPortaSaida;

    public PedidoRepositoryAdapter(PostGresPedidoRepository postGresPedidoRepository, PostGresClienteRepository postGresClienteRepository, PostGresProdutoRepository postGresProdutoRepository, PedidoPagamentoPortaSaida pedidoPagamentoPortaSaida) {
        this.postGresPedidoRepository = postGresPedidoRepository;
        this.postGresClienteRepository = postGresClienteRepository;
        this.postGresProdutoRepository = postGresProdutoRepository;
        this.pedidoPagamentoPortaSaida = pedidoPagamentoPortaSaida;
    }

    @Override
    public PedidoDto criaPedido(PedidoDto pedidoDto) {
        var cliente = postGresClienteRepository.findByCpf(pedidoDto.clienteDto().cpf())
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));
        List<Long> idProdutos = pedidoDto.produtosDtos().stream().map(ProdutoDto::id).toList();
        var listaProdutos = postGresProdutoRepository.findAllById(idProdutos);
        var pagamentoPedido = pagamentoPedido(new PedidoEntity(cliente, listaProdutos, StatusPedido.RECEBIDO));
        return pagamentoPedido.toPedidoDto();
    }

    public PedidoEntity pagamentoPedido(PedidoEntity pedido) {

        var valorTotal = calculaValorTotal(pedido.getProdutos());
        var checkoutPedido = pedidoPagamentoPortaSaida.pagamentoPedido(pedido.getCliente().getCpf(), valorTotal);

        if(!checkoutPedido.status()){
            throw new PaymentRefusedException(checkoutPedido.message());
        }
        return postGresPedidoRepository.save(pedido);
    }

    private BigDecimal calculaValorTotal(List<ProdutoEntity> produtos) {
        return produtos.stream().map(ProdutoEntity::getPreco).reduce(BigDecimal.ZERO, BigDecimal::add);
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