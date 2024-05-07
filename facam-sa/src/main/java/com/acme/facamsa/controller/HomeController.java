package com.acme.facamsa.controller;

import com.acme.facamsa.entity.Aluno;
import com.acme.facamsa.repository.AlunoRepository;
import com.acme.facamsa.request.PesquisarRequest;
import com.acme.facamsa.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;

@Controller
public class HomeController {

    @Autowired
    private AlunoService alunoService;

    @PostMapping("/pesquisar")
    public String pesquisar(@ModelAttribute PesquisarRequest request, Model model) {
        var alunos = alunoService.pesquisar(request.getFiltro());
        var novoRequest = new PesquisarRequest();
        novoRequest.setFiltro(request.getFiltro());
        model.addAttribute("alunos", alunos);
        model.addAttribute("request", novoRequest);
        return "home";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id, Model model) {
        alunoService.excluir(id);
        return "redirect:/";
    }

}
