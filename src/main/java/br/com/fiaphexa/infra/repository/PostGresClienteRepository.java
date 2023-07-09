package br.com.fiaphexa.infra.repository;
import br.com.fiaphexa.infra.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PostGresClienteRepository extends JpaRepository<ClienteEntity, Long> {

    Optional<ClienteEntity> findByCpf(String cpf);
}
