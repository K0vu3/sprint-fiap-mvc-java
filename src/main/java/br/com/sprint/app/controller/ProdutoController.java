package br.com.sprint.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.sprint.app.model.Cliente;
import br.com.sprint.app.model.Produto;
import br.com.sprint.app.repositorie.ClienteRepositorie;
import br.com.sprint.app.repositorie.ProdutoRepositorie;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {

	private final ProdutoRepositorie produtoRepo;
	private final ClienteRepositorie clienteRepo;

	@Autowired
	public ProdutoController(ProdutoRepositorie produtoRepo, ClienteRepositorie clienteRepo) {
		this.produtoRepo = produtoRepo;
		this.clienteRepo = clienteRepo;
	}

	@GetMapping("/create")
	public ModelAndView home() {
		ModelAndView mv = new ModelAndView("create");
		mv.addObject("produto", new Produto());
		return mv;
	}

	public Long determinarClienteId() {
		Long clienteIdPadrao = clienteRepo.obterClienteComIdMaisAlto();

		// se não houver clientes no banco de dados, você pode definir um valor padrão
		// aqui
		if (clienteIdPadrao == null) {
			clienteIdPadrao = 1L; // valor padrão
		}

		return clienteIdPadrao;
	}

	@PostMapping("/create")
	public String create(Produto produto) {
		Long clienteId = determinarClienteId(); // Implemente sua lógica aqui

		// Encontre o cliente pelo ID
		Cliente cliente = clienteRepo.findById(clienteId)
				.orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado: " + clienteId));

		// Associe o cliente ao produto
		produto.setCliente(cliente);

		// Salve o produto no banco de dados
		produtoRepo.save(produto);

		return "redirect:/produtos/list";
	}

	@GetMapping("/list")
	public ModelAndView list() {
		Iterable<Produto> produtos = produtoRepo.findAll();
		ModelAndView mv = new ModelAndView("list");
		mv.addObject("produtos", produtos);
		return mv;
	}

	@GetMapping("/edit/{id}")
	public ModelAndView edit(@PathVariable("id") Long id) {
		Produto produto = produtoRepo.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Produto não encontrado: " + id));

		ModelAndView mv = new ModelAndView("create");
		mv.addObject("produto", produto);
		return mv;
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id) {
		produtoRepo.deleteById(id);
		return "redirect:/produtos/list";
	}

}
