package br.com.fiaphexa.infra.persistence.cliente;

import br.com.fiaphexa.aplicacao.dtos.cliente.ClienteDto;
import br.com.fiaphexa.aplicacao.repositorios.cliente.ClienteRepositoryService;
import br.com.fiaphexa.infra.entity.ClienteEntity;
import br.com.fiaphexa.infra.repository.PostGresClienteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ClientePersistenceImpl implements ClienteRepositoryService {

    private final PostGresClienteRepository postGresClienteRepository;

    public ClientePersistenceImpl(PostGresClienteRepository postGresClienteRepository) {
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
        postGresClienteRepository.deleteByCpf(cpf);
    }
}
