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

    private static final  String  CLIENTE_NAO_ENCONTRADO = "Cliente não encontrado";

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
        var clienteEntity = postGresClienteRepository.findByCpf(cpf)
                .orElseThrow(() -> new EntityNotFoundException(CLIENTE_NAO_ENCONTRADO));
        postGresClienteRepository.deleteById(clienteEntity.getId());
    }

    public ClienteDto atualizaCliente(ClienteDto clienteDto) {

        var clienteEntity = postGresClienteRepository.findByCpf(clienteDto.cpf()).orElseThrow(
                () -> new EntityNotFoundException(CLIENTE_NAO_ENCONTRADO)
        );
        BeanUtils.copyProperties(clienteDto, clienteEntity, "id");
        var clienteEntityAtualizado = postGresClienteRepository.save(clienteEntity);
        return clienteEntityAtualizado.toClienteDto();
    }
}
