package br.com.sprint.app.repositorie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.sprint.app.model.Produto;

@Repository
public interface ProdutoRepositorie extends JpaRepository<Produto, Long> {

}
