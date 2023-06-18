package br.com.fiaplanches.service;

import br.com.fiaplanches.model.Cliente;
import br.com.fiaplanches.model.Produto;
import br.com.fiaplanches.records.CriarCliente;
import br.com.fiaplanches.records.PedidoRecord;
import br.com.fiaplanches.records.RetornaProduto;
import br.com.fiaplanches.records.RetornoCliente;
import br.com.fiaplanches.repository.ClienteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ClienteService {

    private final ClienteRepository clienteRepository;

    private static final String CLIENTE_NAO_ENCONTRADO = "Cliente não encontrado";

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public RetornoCliente buscaClienteCpf(Long cpf){
        var cliente = clienteRepository.findByCpf(cpf)
                .orElseThrow(() -> new EntityNotFoundException(CLIENTE_NAO_ENCONTRADO));
        return new RetornoCliente(cliente.getId(), cliente.getCpf(), cliente.getNome());
    }

    public RetornoCliente criarCliente(CriarCliente cliente){
        var criaCliente = new Cliente(cliente);
       return new RetornoCliente(clienteRepository.save(criaCliente));

    }

    public RetornoCliente updateCliente(CriarCliente updateCliente) {
        var cliente = clienteRepository.findByCpf(updateCliente.cpf())
                .orElseThrow(() -> new EntityNotFoundException(CLIENTE_NAO_ENCONTRADO));
        cliente.setNome(updateCliente.nome());
        clienteRepository.save(cliente);
        return new RetornoCliente(cliente);
    }

    public void removeCliente(CriarCliente removeCliente) {
        var cliente = clienteRepository.findByCpf(removeCliente.cpf())
                .orElseThrow(() -> new EntityNotFoundException(CLIENTE_NAO_ENCONTRADO));
        clienteRepository.delete(cliente);
    }

    public List<PedidoRecord> buscaPedidosCliente(Long idCliente) {
        var cliente = clienteRepository.findById(idCliente)
        .orElseThrow(() -> new EntityNotFoundException(CLIENTE_NAO_ENCONTRADO));
        return cliente.getPedidos().stream().map(
                pedido -> new PedidoRecord(
                        pedido.getCliente().getCpf(),
                        pedido.getListaProdutosRecord(),
                        pedido.getId(),
                        pedido.getDataPedido() )
        ).toList();
    }
}

