package br.com.fiaphexa.infra.adapter.cliente;

import br.com.fiaphexa.dominio.dtos.cliente.ClienteDto;
import br.com.fiaphexa.dominio.portas.saida.cliente.ClienteRepositoryPortaSaida;
import br.com.fiaphexa.infra.entity.ClienteEntity;
import br.com.fiaphexa.infra.repository.PostGresClienteRepository;
import org.springframework.stereotype.Component;

@Component
public class ClienteRepositoryAdapter implements ClienteRepositoryPortaSaida {

    private final PostGresClienteRepository postGresClienteRepository;

    public ClienteRepositoryAdapter(PostGresClienteRepository postGresClienteRepository) {
        this.postGresClienteRepository = postGresClienteRepository;
    }

    @Override
    public ClienteDto salvaCliente(ClienteDto clienteDto) {
        var clienteEntity = postGresClienteRepository.save(new ClienteEntity(clienteDto));
        return clienteEntity.toClienteDto();
    }

    @Override
    public ClienteDto procuraClientePorCpf(Long cpf) {
        return null;
    }

    @Override
    public void removeCliente(Long id) {

    }
}
