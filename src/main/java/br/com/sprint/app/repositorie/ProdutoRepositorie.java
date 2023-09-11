package br.com.sprint.app.repositorie;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sprint.app.model.Produto;

public interface ProdutoRepositorie extends JpaRepository<Produto, Long> {

}
