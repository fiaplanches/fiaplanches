package br.com.fiaplanches.controller;

import br.com.fiaplanches.model.Cliente;
import br.com.fiaplanches.records.CriarCliente;
import br.com.fiaplanches.records.RetornoCliente;
import br.com.fiaplanches.repository.ClienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @PostMapping("/criarCliente")
    @Transactional
    public ResponseEntity criarCliente(@RequestBody CriarCliente cliente, UriComponentsBuilder uriBuilder){
        var criaCliente = new Cliente(cliente);
        clienteRepository.save(criaCliente);

        var uri = uriBuilder.path("/cliente/{cpf}").buildAndExpand(criaCliente.getCpf()).toUri();
        var retornoCliente = new RetornoCliente(criaCliente.getId(), criaCliente.getCpf(), criaCliente.getNome());

        return ResponseEntity.created(uri).body(retornoCliente);
    }

    @GetMapping(path = "/{cpf}")
    public ResponseEntity buscaClienteCpf(@PathVariable Long cpf) {
        var cliente = clienteRepository.findByCpf(cpf);
        return ResponseEntity.ok(new RetornoCliente(cliente.getId(), cliente.getCpf(), cliente.getNome()));
    }

    @Transactional
    @PutMapping(path = "/cpf")
    public ResponseEntity updateDataDoctor(@RequestBody CriarCliente updateCliente) {
        var cliente = clienteRepository.findByCpf(updateCliente.cpf());
        var retornoCliente = new RetornoCliente(cliente.getId(), updateCliente.cpf(), updateCliente.nome());
        cliente.atualizaCliente(retornoCliente);
        return ResponseEntity.ok(retornoCliente);
    }

    @Transactional
    @DeleteMapping("/cpf")
    public ResponseEntity removeCliente(@RequestBody CriarCliente removeCliente) {
        var cliente = clienteRepository.findByCpf(removeCliente.cpf());
        clienteRepository.findById(cliente.getId());
        return ResponseEntity.ok("Usuário excluido com sucesso!");
    }

}
