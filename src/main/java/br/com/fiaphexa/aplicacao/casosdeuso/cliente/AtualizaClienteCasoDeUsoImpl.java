package br.com.fiaphexa.aplicacao.casosdeuso.cliente;

import br.com.fiaphexa.aplicacao.dtos.cliente.ClienteDto;
import br.com.fiaphexa.aplicacao.casosdeuso.abstracoes.clientes.AtualizaClienteCasoDeUso;
import br.com.fiaphexa.aplicacao.repositorios.cliente.ClienteRepositoryService;
import br.com.fiaphexa.dominio.model.Cliente;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
@Slf4j

public class AtualizaClienteCasoDeUsoImpl implements AtualizaClienteCasoDeUso {

    private final ClienteRepositoryService clienteRepositoryService;

    public AtualizaClienteCasoDeUsoImpl(ClienteRepositoryService clienteRepositoryService) {
        this.clienteRepositoryService = clienteRepositoryService;
    }

    @Override
    public ClienteDto atualiza(ClienteDto clienteDtoNew) {
        log.info("Atualizando cliente para o CPF: " + clienteDtoNew.cpf());
        ClienteDto clienteDtoOld = clienteRepositoryService.procuraClientePorCpf(clienteDtoNew.cpf()).orElseThrow(
                () -> new EntityNotFoundException("Cliente nao encontrado.")
        );

        Cliente clienteNew = clienteDtoNew.toCliente();
        Cliente clienteOld = clienteDtoOld.toCliente();
        BeanUtils.copyProperties(clienteNew, clienteOld, "id");

        ClienteDto clienteDtoAtualizado = ClienteDto.toClienteDto(clienteOld);

        var clienteDtoSaved = clienteRepositoryService.salvaCliente(clienteDtoAtualizado);
        log.info("Cliente atualizado com sucesso.");

        return clienteDtoSaved;
    }
}
