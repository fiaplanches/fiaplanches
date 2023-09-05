package br.com.fiaphexa.aplicacao.casosdeuso.pedido;

import br.com.fiaphexa.aplicacao.casosdeuso.abstracoes.pedidos.AtualizaPedidoCasoDeUso;
import br.com.fiaphexa.aplicacao.dtos.pedido.PedidoDto;
import br.com.fiaphexa.aplicacao.repositorios.pedido.PedidoRepositoryService;
import br.com.fiaphexa.dominio.enuns.StatusPedido;
import br.com.fiaphexa.infra.entity.PedidoEntity;
import jakarta.persistence.EntityNotFoundException;

import java.security.InvalidParameterException;

public class AtualizaPedidoCasoDeUsoImpl implements AtualizaPedidoCasoDeUso {

    private final PedidoRepositoryService pedidoRepositoryService;

    public AtualizaPedidoCasoDeUsoImpl(PedidoRepositoryService pedidoRepositoryService) {
        this.pedidoRepositoryService = pedidoRepositoryService;
    }

    @Override
    public PedidoDto atualizaPedido(Long id) {

        PedidoDto pedido = pedidoRepositoryService.buscaPedidoPorId(id).orElseThrow(
                () -> new EntityNotFoundException("Pedido não encontrado")
        );

        StatusPedido novoStatusPedido = null;

        switch (pedido.statusPedido()){
            case NO_CARRINHO: novoStatusPedido = StatusPedido.PAGAMENTO_PENDENTE; break;
            case PAGAMENTO_PENDENTE: novoStatusPedido = StatusPedido.RECEBIDO; break;
            case RECEBIDO: novoStatusPedido = StatusPedido.EM_PREPARO; break;
            case EM_PREPARO: novoStatusPedido = StatusPedido.PRONTO; break;
            case PRONTO: novoStatusPedido = StatusPedido.FINALIZADO; break;
            case FINALIZADO: throw new InvalidParameterException("O pedido já está finalizado");
        }

        return pedidoRepositoryService.atualizaPedido(pedido, novoStatusPedido);
    }
}
