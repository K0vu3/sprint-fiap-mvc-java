package br.com.sprint.app.repositorie;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sprint.app.model.Cliente;

public interface ClienteRepositorie extends JpaRepository<Cliente, Long> {

}
