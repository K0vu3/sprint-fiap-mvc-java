package br.com.sprint.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.sprint.app.model.Cliente;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    private List<Cliente> clientes = new ArrayList<>();

    @GetMapping("/list")
    public String listClientes(Model model) {
        model.addAttribute("clientes", clientes);
        return "listCliente";
    }

    @GetMapping("/create")
    public String showForm(Cliente cliente) {
        return "createCliente";
    }

    @PostMapping("/create")
    public String createCliente(Cliente cliente) {

        Long id = clientes.isEmpty() ? 1L : clientes.get(clientes.size() - 1).getId() + 1;
        cliente.setId(id);

        clientes.add(cliente);

        return "redirect:/clientes/list";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id) {
        ModelAndView mv = new ModelAndView("createCliente");

        Cliente clienteFind = clientes.stream().filter(cliente -> id.equals(cliente.getId())).findFirst().get();
        mv.addObject("cliente", clienteFind);

        return mv;
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {

        for (Cliente cliente : clientes) {

            if (cliente.getId().equals(id)) {
                clientes.remove(cliente);
                break;
            }
        }

        return "redirect:/clientes/list";
    }
}
