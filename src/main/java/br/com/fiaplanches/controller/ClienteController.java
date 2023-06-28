package br.com.fiaplanches.controller;

import br.com.fiaplanches.records.CriarCliente;
import br.com.fiaplanches.records.RetornoCliente;
import br.com.fiaplanches.service.ClienteService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/cliente")
public class ClienteController {


    @Autowired
    private ClienteService clienteService;

    @PostMapping("/criarCliente")
    public ResponseEntity<RetornoCliente> criarCliente(@RequestBody @Valid CriarCliente cliente, UriComponentsBuilder uriBuilder){
       RetornoCliente retornoCliente = clienteService.criarCliente(cliente);
        var uri = uriBuilder.path("/cliente/{cpf}").buildAndExpand(retornoCliente.cpf()).toUri();
        return ResponseEntity.created(uri).body(retornoCliente);
    }

    @GetMapping(path = "/{cpf}")
    public ResponseEntity<RetornoCliente> buscaClienteCpf(@PathVariable Long cpf) {
        RetornoCliente retornoCliente = clienteService.buscaClienteCpf(cpf);
        return ResponseEntity.ok(retornoCliente);
    }

    @PutMapping(path = "/cpf")
    public ResponseEntity<RetornoCliente> updateCliente(@RequestBody CriarCliente updateCliente) {
        RetornoCliente retornoCliente = clienteService.updateCliente(updateCliente);
        return ResponseEntity.ok(retornoCliente);
    }

    @Transactional
    @DeleteMapping("/{cpf}")
    public ResponseEntity<String> removeCliente(@PathVariable Long cpf) {
        clienteService.removeCliente(cpf);
        return ResponseEntity.ok("Usuário excluido com sucesso!");
    }

}
