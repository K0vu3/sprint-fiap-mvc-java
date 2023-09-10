package br.com.sprint.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.sprint.app.model.Cliente;

@Controller
public class ClienteController {

    List<Cliente> clientes = new ArrayList<>();

    @GetMapping("/create")
    public ModelAndView home() {
        ModelAndView mv = new ModelAndView("create");
        mv.addObject("produto", new Cliente());
        return mv;
    }

    @PostMapping("/create")
    public String create(Cliente cliente) {

        if (cliente.getId() != null) {
            Cliente produtoFind = clientes.stream().filter(clienteItem -> cliente.getId().equals(clienteItem.getId()))
                    .findFirst().get();
            clientes.set(clientes.indexOf(produtoFind), cliente);
        } else {
            Long id = clientes.size() + 1L;
            clientes.add(new Cliente(id, cliente.getCnpj(), cliente.getDate()));
        }

        return "redirect:/list";
    }

    @GetMapping("/list")
    public ModelAndView list() {
        ModelAndView mv = new ModelAndView("list");
        mv.addObject("clientes", clientes);
        return mv;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id) {
        ModelAndView mv = new ModelAndView("create");

        Cliente clienteFind = clientes.stream().filter(produto -> id.equals(produto.getId())).findFirst().get();
        mv.addObject("produto", clienteFind);

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

        return "redirect:/list";
    }

}
