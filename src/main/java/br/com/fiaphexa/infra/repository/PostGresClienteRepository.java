package br.com.fiaphexa.infra.repository;

import br.com.fiaphexa.infra.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostGresClienteRepository extends JpaRepository<ClienteEntity, Long> {
}
