package br.com.fiaplanches.service;

import br.com.fiaplanches.error.PaymentRefusedException;
import br.com.fiaplanches.model.Pedido;
import br.com.fiaplanches.model.Produto;
import br.com.fiaplanches.records.CriarPedidoRecord;
import br.com.fiaplanches.repository.ClienteRepository;
import br.com.fiaplanches.repository.PedidoRepository;
import br.com.fiaplanches.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private MercadoPagoCheckout mercadoPagoCheckout;

    public Pedido criarPedido(CriarPedidoRecord pedidoRecord) {
        var cliente = clienteRepository.findByCpf(pedidoRecord.cpf())
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));
        List<Long> idProdutos = pedidoRecord.produtos();
        var listaProdutos = produtoRepository.findAllById(idProdutos);

        var pedido = pagamentoPedido(new Pedido(cliente, listaProdutos));
        return pedido;
    }

    public Pedido pagamentoPedido(Pedido pedido) {

        BigDecimal valorTotal = pedido.getProdutos().stream()
                .map(Produto::getPreco)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        var checkoutRecord = mercadoPagoCheckout.checkout(pedido.getCliente().getCpf(), valorTotal);
        if(!checkoutRecord.status()){
            throw new PaymentRefusedException(checkoutRecord.message());
        }
        return pedidoRepository.save(pedido);
    }

}
