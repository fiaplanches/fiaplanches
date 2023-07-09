package br.com.fiaphexa.aplicacao.controllers.cliente;

import br.com.fiaphexa.aplicacao.controllers.cliente.request.ClienteRequestDto;
import br.com.fiaphexa.aplicacao.controllers.cliente.response.ClienteResponseDto;
import br.com.fiaphexa.dominio.portas.entrada.clientes.AtualizaClientePortaEntrada;
import br.com.fiaphexa.dominio.portas.entrada.clientes.CadastraClientePortaEntrada;
import br.com.fiaphexa.dominio.portas.entrada.clientes.ProcuraClientePortaEntrada;
import br.com.fiaphexa.dominio.portas.entrada.clientes.RemoveClientePortaEntrada;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/cliente")
public class ClienteControllerAdapter {

    private final CadastraClientePortaEntrada cadastraClientePortaEntrada;
    private final ProcuraClientePortaEntrada procuraClientePortaEntrada;
    private final AtualizaClientePortaEntrada atualizaClientePortaEntrada;
    private final RemoveClientePortaEntrada removeClientePortaEntrada;

    public ClienteControllerAdapter(CadastraClientePortaEntrada cadastraClientePortaEntrada, ProcuraClientePortaEntrada procuraClientePortaEntrada, AtualizaClientePortaEntrada atualizaClientePortaEntrada, RemoveClientePortaEntrada removeClientePortaEntrada) {
        this.cadastraClientePortaEntrada = cadastraClientePortaEntrada;
        this.procuraClientePortaEntrada = procuraClientePortaEntrada;
        this.atualizaClientePortaEntrada = atualizaClientePortaEntrada;
        this.removeClientePortaEntrada = removeClientePortaEntrada;
    }

    @PostMapping("/criarCliente")
    public ResponseEntity<ClienteResponseDto> criarCliente(@RequestBody @Valid ClienteRequestDto clienteRequestDto, UriComponentsBuilder uriBuilder){
        var clienteDto = cadastraClientePortaEntrada.cadastra(clienteRequestDto.toClienteDto());
        var clienteResponse = ClienteResponseDto.toClienteResponseDto(clienteDto);
        var uri = uriBuilder.path("/cliente/{cpf}").buildAndExpand(clienteResponse.cpf()).toUri();
        return ResponseEntity.created(uri).body(clienteResponse);
    }

    @GetMapping(path = "/{cpf}")
    public ResponseEntity<ClienteResponseDto> buscaClienteCpf(@PathVariable String cpf) {
        return ResponseEntity.ok(ClienteResponseDto.toClienteResponseDto(procuraClientePortaEntrada.procuraByCpf(cpf)));
    }

    @PutMapping(path = "/cpf")
    public ResponseEntity<ClienteResponseDto> updateCliente(@RequestBody ClienteRequestDto updateCliente) {
        return ResponseEntity.ok(ClienteResponseDto.toClienteResponseDto(atualizaClientePortaEntrada.atualiza(updateCliente.toClienteDto())));
    }

    @Transactional
    @DeleteMapping("/{cpf}")
    public ResponseEntity<String> removeCliente(@PathVariable String cpf) {
        removeClientePortaEntrada.remove(cpf);
        return ResponseEntity.ok("Usuário excluido com sucesso!");
    }
}
