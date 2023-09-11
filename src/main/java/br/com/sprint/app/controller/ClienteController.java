package br.com.sprint.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.sprint.app.model.Cliente;
import br.com.sprint.app.repositorie.ClienteRepositorie;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteRepositorie clienteRepo;

    @Autowired
    public ClienteController(ClienteRepositorie clienteRepo) {
        this.clienteRepo = clienteRepo;
    }

    @GetMapping("/list")
    public String listClientes(Model model) {
        Iterable<Cliente> clientes = clienteRepo.findAll();
        model.addAttribute("clientes", clientes);
        return "listCliente";
    }

    @GetMapping("/create")
    public String showForm(Cliente cliente) {
        return "createCliente";
    }

    @PostMapping("/create")
    public String createCliente(Cliente cliente) {
        if (cliente.getId() != null) {
            Cliente clienteExistente = clienteRepo.findById(cliente.getId()).orElse(null);
            if (clienteExistente != null) {
                clienteExistente.setNome(cliente.getNome());
                clienteExistente.setEmail(cliente.getEmail());
                clienteRepo.save(clienteExistente);
            }
        } else {
            clienteRepo.save(cliente);
        }

        return "redirect:/clientes/list";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id) {
        ModelAndView mv = new ModelAndView("createCliente");
        Cliente cliente = clienteRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado: " + id));
        mv.addObject("cliente", cliente);
        return mv;
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        clienteRepo.deleteById(id);
        return "redirect:/clientes/list";
    }
}
