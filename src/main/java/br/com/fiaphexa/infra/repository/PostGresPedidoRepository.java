package br.com.fiaphexa.infra.repository;

import br.com.fiaphexa.infra.entity.PedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostGresPedidoRepository extends JpaRepository<PedidoEntity, Long> {
}
