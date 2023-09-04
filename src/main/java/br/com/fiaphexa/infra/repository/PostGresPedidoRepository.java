package br.com.fiaphexa.infra.repository;

import br.com.fiaphexa.infra.entity.PedidoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PostGresPedidoRepository extends JpaRepository<PedidoEntity, Long> {

    @Query("select p from PedidoEntity p where p.cliente.cpf = :cpf")
    Page<PedidoEntity> findByCpfCliente(String cpf, Pageable pageable);

    @Query("select p from PedidoEntity p where p.id = :idPedido")
    Optional<PedidoEntity> findByIdPedido(Long idPedido);
}
