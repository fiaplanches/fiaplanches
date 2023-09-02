package br.com.fiaphexa.web.controllers.cliente;

import br.com.fiaphexa.web.controllers.cliente.request.ClienteRequestDto;
import br.com.fiaphexa.web.controllers.cliente.response.ClienteResponseDto;
import br.com.fiaphexa.aplicacao.casosdeuso.abstracoes.clientes.AtualizaClienteCasoDeUso;
import br.com.fiaphexa.aplicacao.casosdeuso.abstracoes.clientes.CadastraClienteCasoDeUso;
import br.com.fiaphexa.aplicacao.casosdeuso.abstracoes.clientes.ProcuraClienteCasoDeUso;
import br.com.fiaphexa.aplicacao.casosdeuso.abstracoes.clientes.RemoveClienteCasoDeUso;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    private final CadastraClienteCasoDeUso cadastraClienteCasoDeUso;
    private final ProcuraClienteCasoDeUso procuraClienteCasoDeUso;
    private final AtualizaClienteCasoDeUso atualizaClienteCasoDeUso;
    private final RemoveClienteCasoDeUso removeClienteCasoDeUso;

    public ClienteController(CadastraClienteCasoDeUso cadastraClienteCasoDeUso, ProcuraClienteCasoDeUso procuraClienteCasoDeUso, AtualizaClienteCasoDeUso atualizaClienteCasoDeUso, RemoveClienteCasoDeUso removeClienteCasoDeUso) {
        this.cadastraClienteCasoDeUso = cadastraClienteCasoDeUso;
        this.procuraClienteCasoDeUso = procuraClienteCasoDeUso;
        this.atualizaClienteCasoDeUso = atualizaClienteCasoDeUso;
        this.removeClienteCasoDeUso = removeClienteCasoDeUso;
    }

    @PostMapping("/criarCliente")
    public ResponseEntity<ClienteResponseDto> criarCliente(@RequestBody @Valid ClienteRequestDto clienteRequestDto, UriComponentsBuilder uriBuilder){
        var clienteDto = cadastraClienteCasoDeUso.cadastra(clienteRequestDto.toClienteDto());
        var clienteResponse = ClienteResponseDto.toClienteResponseDto(clienteDto);
        var uri = uriBuilder.path("/cliente/{cpf}").buildAndExpand(clienteResponse.cpf()).toUri();
        return ResponseEntity.created(uri).body(clienteResponse);
    }

    @GetMapping(path = "/{cpf}")
    public ResponseEntity<ClienteResponseDto> buscaClienteCpf(
            @PathVariable
            @NotBlank(message = "Número do CPF não pode ser vazio")
            @CPF(message = "CPF informado invalido")
            String cpf
    ) {
        return ResponseEntity.ok(ClienteResponseDto.toClienteResponseDto(procuraClienteCasoDeUso.procuraPorCpf(cpf)));
    }

    @PutMapping(path = "/cpf")
    public ResponseEntity<ClienteResponseDto> updateCliente(@RequestBody @Valid ClienteRequestDto updateCliente) {
        return ResponseEntity.ok(ClienteResponseDto.toClienteResponseDto(atualizaClienteCasoDeUso.atualiza(updateCliente.toClienteDto())));
    }

    @Transactional
    @DeleteMapping("/{cpf}")
    public ResponseEntity<String> removeCliente(
            @PathVariable
            @NotBlank(message = "Número do CPF não pode ser vazio")
            @CPF(message = "CPF informado invalido")
            String cpf
    ) {
        removeClienteCasoDeUso.remove(cpf);
        return ResponseEntity.ok("Usuário excluido com sucesso!");
    }
}
