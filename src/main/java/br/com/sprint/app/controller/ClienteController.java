package br.com.sprint.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.sprint.app.model.Cliente;

public class ClienteController {

    List<Cliente> clientes = new ArrayList<>();

    @GetMapping("/create-cliente")
    public ModelAndView home() {
        ModelAndView mv = new ModelAndView("create-cliente");
        mv.addObject("cliente", new Cliente());
        return mv;
    }

    @PostMapping("/create-cliente")
    public String create(Cliente cliente) {
        if (cliente.getId() != null) {
            Cliente clienteFind = clientes.stream().filter(clienteItem -> cliente.getId().equals(clienteItem.getId()))
                    .findFirst().get();
            clientes.set(clientes.indexOf(clienteFind), cliente);
        } else {
            Long id = clientes.size() + 1L;
            clientes.add(new Cliente(id, cliente.getCnpj(), cliente.getDate()));
        }
        return "redirect:/list-cliente";
    }

    @GetMapping("/list-cliente")
    public ModelAndView list() {
        ModelAndView mv = new ModelAndView("list-cliente");
        mv.addObject("clientes", clientes);
        return mv;
    }

    @GetMapping("/edit-cliente/{id}")
    public ModelAndView edit(@PathVariable("id") Long id) {
        ModelAndView mv = new ModelAndView("create-cliente");

        Cliente clienteFind = clientes.stream().filter(cliente -> id.equals(cliente.getId())).findFirst().get();
        mv.addObject("cliente", clienteFind);

        return mv;
    }

    @GetMapping("/delete-cliente/{id}")
    public String delete(@PathVariable("id") Long id) {

        for (Cliente cliente : clientes) {

            if (cliente.getId().equals(id)) {
                clientes.remove(cliente);
                break;
            }
        }

        return "redirect:/list-cliente";
    }

}
