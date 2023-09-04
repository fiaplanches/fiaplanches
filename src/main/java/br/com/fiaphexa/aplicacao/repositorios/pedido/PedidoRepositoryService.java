package br.com.fiaphexa.aplicacao.repositorios.pedido;

import br.com.fiaphexa.aplicacao.dtos.PageInfoDto;
import br.com.fiaphexa.aplicacao.dtos.pedido.PedidoDto;

import java.util.List;
import java.util.Optional;

public interface PedidoRepositoryService {
    List<PedidoDto> buscaPedidosCliente(String cpf, PageInfoDto page);
    List<PedidoDto> buscaPedidos(PageInfoDto page);
    PedidoDto salvaPedido(PedidoDto pedidoDto);
    Boolean atualizaStatusPagamento(Long idPedido, Boolean isApproved);
    Optional<PedidoDto> buscaPedidoPorId(Long idPedido);
}
