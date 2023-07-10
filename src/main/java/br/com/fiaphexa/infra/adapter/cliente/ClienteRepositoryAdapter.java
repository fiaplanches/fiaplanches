package br.com.fiaphexa.infra.adapter.cliente;

import br.com.fiaphexa.dominio.dtos.cliente.ClienteDto;
import br.com.fiaphexa.dominio.portas.saida.cliente.ClienteRepositoryPortaSaida;
import br.com.fiaphexa.infra.entity.ClienteEntity;
import br.com.fiaphexa.infra.repository.PostGresClienteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ClienteRepositoryAdapter implements ClienteRepositoryPortaSaida {

    private final PostGresClienteRepository postGresClienteRepository;

    private static final  String  CLIENTE_NAO_ENCONTRADO = "Cliente não encontrado";

    public ClienteRepositoryAdapter(PostGresClienteRepository postGresClienteRepository) {
        this.postGresClienteRepository = postGresClienteRepository;
    }

    @Override
    public ClienteDto salvaCliente(ClienteDto clienteDto) {
        var clienteEntity = postGresClienteRepository.save(new ClienteEntity(clienteDto));
        return clienteEntity.toClienteDto();
    }

    @Override
    public Optional<ClienteDto> procuraClientePorCpf(String cpf) {
        return postGresClienteRepository.findByCpf(cpf).map(ClienteEntity::toClienteDto);
    }

    @Override
    public void removeCliente(String cpf) {
        var clienteEntity = postGresClienteRepository.findByCpf(cpf)
                .orElseThrow(() -> new EntityNotFoundException(CLIENTE_NAO_ENCONTRADO));
        postGresClienteRepository.deleteById(clienteEntity.getId());
    }

    public ClienteDto atualizaCliente(ClienteDto clienteDto) {
        if (!postGresClienteRepository.existsById(clienteDto.id())) {
            throw new EntityNotFoundException(CLIENTE_NAO_ENCONTRADO);
        }
        var clienteEntity = postGresClienteRepository.save(new ClienteEntity(clienteDto));
        return clienteEntity.toClienteDto();
    }
}
