package br.com.fiaplanches.service;

import br.com.fiaplanches.model.Cliente;
import br.com.fiaplanches.records.CriarCliente;
import br.com.fiaplanches.records.RetornoCliente;
import br.com.fiaplanches.repository.ClienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public RetornoCliente buscaClienteCpf(Long cpf){
        var cliente = clienteRepository.findByCpf(cpf);
        return new RetornoCliente(cliente.getId(), cliente.getCpf(), cliente.getNome());
    }

    public RetornoCliente criarCliente(CriarCliente cliente){
        var criaCliente = new Cliente(cliente);
       return new RetornoCliente(clienteRepository.save(criaCliente));

    }

    public RetornoCliente updateCliente(CriarCliente updateCliente) {
        var cliente = clienteRepository.findByCpf(updateCliente.cpf());
        cliente.setNome(updateCliente.nome());
        clienteRepository.save(cliente);
        return new RetornoCliente(cliente);
    }

    public void removeCliente(CriarCliente removeCliente) {
        var cliente = clienteRepository.findByCpf(removeCliente.cpf());
        clienteRepository.delete(cliente);
    }
}

