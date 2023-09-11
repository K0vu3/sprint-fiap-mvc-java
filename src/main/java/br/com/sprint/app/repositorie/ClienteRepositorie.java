package br.com.sprint.app.repositorie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.sprint.app.model.Cliente;

public interface ClienteRepositorie extends JpaRepository<Cliente, Long> {
	 @Query("SELECT MAX(c.id) FROM Cliente c")
	    Long obterClienteComIdMaisAlto();
}
