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

    public RetornoCliente criarCliente(CriarCliente cliente){
        var criaCliente = new Cliente(cliente);
       return new RetornoCliente(clienteRepository.save(criaCliente));

    }

}

