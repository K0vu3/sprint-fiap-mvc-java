package br.com.sprint.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.sprint.app.model.Produto;

@Controller
public class ProdutoController {

    List<Produto> produtos = new ArrayList<>();

    @GetMapping("/create")
    public String home() {
        return "create";
    }

    @PostMapping("/create")
    public String create(Produto produto) {
        Long id = produtos.size() + 1L;
        produtos.add(new Produto(id, produto.getNome(), produto.getDate()));

        return "redirect:/list";
    }

    @GetMapping("/list")
    public ModelAndView list() {
        ModelAndView mv = new ModelAndView("list");
        mv.addObject("produtos", produtos);
        return mv;
    }

}
