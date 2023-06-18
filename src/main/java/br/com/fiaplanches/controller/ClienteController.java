package br.com.fiaplanches.controller;

import br.com.fiaplanches.records.CriarCliente;
import br.com.fiaplanches.records.RetornoCliente;
import br.com.fiaplanches.repository.ClienteRepository;
import br.com.fiaplanches.service.ClienteService;
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

    @Autowired
    private ClienteService clienteService;

    @PostMapping("/criarCliente")
    @Transactional
    public ResponseEntity<RetornoCliente> criarCliente(@RequestBody CriarCliente cliente, UriComponentsBuilder uriBuilder){
       RetornoCliente retornoCliente = clienteService.criarCliente(cliente);
        var uri = uriBuilder.path("/cliente/{cpf}").buildAndExpand(retornoCliente).toUri();
        return ResponseEntity.created(uri).body(retornoCliente);
    }

    @GetMapping(path = "/{cpf}")
    public ResponseEntity<RetornoCliente> buscaClienteCpf(@PathVariable Long cpf) {
        var cliente = clienteRepository.findByCpf(cpf);
        return ResponseEntity.ok(new RetornoCliente(cliente.getId(), cliente.getCpf(), cliente.getNome()));
    }

    @Transactional
    @PutMapping(path = "/cpf")
    public ResponseEntity<RetornoCliente> updateDataDoctor(@RequestBody CriarCliente updateCliente) {
        var cliente = clienteRepository.findByCpf(updateCliente.cpf());
        var retornoCliente = new RetornoCliente(cliente.getId(), updateCliente.cpf(), updateCliente.nome());
        cliente.atualizaCliente(retornoCliente);
        return ResponseEntity.ok(retornoCliente);
    }

    @Transactional
    @DeleteMapping("/cpf")
    public ResponseEntity<String> removeCliente(@RequestBody CriarCliente removeCliente) {
        var cliente = clienteRepository.findByCpf(removeCliente.cpf());
        clienteRepository.findById(cliente.getId());
        return ResponseEntity.ok("Usuário excluido com sucesso!");
    }

}
