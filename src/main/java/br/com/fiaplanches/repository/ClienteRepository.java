package br.com.fiaplanches.repository;

import br.com.fiaplanches.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    public Cliente findByCpf(Long cpf);

}
