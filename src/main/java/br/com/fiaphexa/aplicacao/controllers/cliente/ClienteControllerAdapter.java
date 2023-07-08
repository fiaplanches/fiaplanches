package br.com.fiaphexa.aplicacao.controllers.cliente;

import br.com.fiaphexa.aplicacao.controllers.cliente.request.ClienteRequestDto;
import br.com.fiaphexa.aplicacao.controllers.cliente.response.ClienteResponseDto;
import br.com.fiaphexa.dominio.portas.entrada.clientes.CadastraClientePortaEntrada;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/cliente")
public class ClienteControllerAdapter {

    private final CadastraClientePortaEntrada cadastraClientePortaEntrada;

    public ClienteControllerAdapter(CadastraClientePortaEntrada cadastraClientePortaEntrada) {
        this.cadastraClientePortaEntrada = cadastraClientePortaEntrada;
    }

    @PostMapping("/criarCliente")
    public ResponseEntity<ClienteResponseDto> criarCliente(@RequestBody @Valid ClienteRequestDto clienteRequestDto, UriComponentsBuilder uriBuilder){
        var clienteDto = cadastraClientePortaEntrada.cadastra(clienteRequestDto.toClienteDto());
        var clienteResponse = ClienteResponseDto.toClienteResponseDto(clienteDto);
        var uri = uriBuilder.path("/cliente/{cpf}").buildAndExpand(clienteResponse.cpf()).toUri();
        return ResponseEntity.created(uri).body(clienteResponse);
    }

//    @GetMapping(path = "/{cpf}")
//    public ResponseEntity<RetornoCliente> buscaClienteCpf(@PathVariable Long cpf) {
//        RetornoCliente retornoCliente = clienteService.buscaClienteCpf(cpf);
//        return ResponseEntity.ok(retornoCliente);
//    }
//
//    @PutMapping(path = "/cpf")
//    public ResponseEntity<RetornoCliente> updateCliente(@RequestBody CriarCliente updateCliente) {
//        RetornoCliente retornoCliente = clienteService.updateCliente(updateCliente);
//        return ResponseEntity.ok(retornoCliente);
//    }
//
//    @Transactional
//    @DeleteMapping("/{cpf}")
//    public ResponseEntity<String> removeCliente(@PathVariable Long cpf) {
//        clienteService.removeCliente(cpf);
//        return ResponseEntity.ok("Usuário excluido com sucesso!");
//    }
}
