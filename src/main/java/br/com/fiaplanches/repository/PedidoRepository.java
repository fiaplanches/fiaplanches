package br.com.fiaplanches.repository;

import br.com.fiaplanches.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
