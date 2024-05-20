package com.acme.facamsa.controller;

import com.acme.facamsa.entity.Aluno;
import com.acme.facamsa.service.AlunoService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/aluno")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @PostMapping("/pesquisar")
    public String pesquisar(@ModelAttribute PesquisarRequest request, Model model) {
        var alunos = alunoService.pesquisar(request.getFiltro());
        var novoRequest = new PesquisarRequest();
        novoRequest.setFiltro(request.getFiltro());
        model.addAttribute("alunos", alunos);
        model.addAttribute("request", novoRequest);
        return "aluno/index";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        alunoService.excluir(id);
        return "redirect:/";
    }


    @GetMapping("/form")
    public String form(Model model) {
        model.addAttribute("aluno", new Aluno());
        return "aluno/form";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Aluno aluno) {
        alunoService.salvar(aluno);
        return "redirect:/";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        var aluno = alunoService.obterAluno(id);
        model.addAttribute("aluno", aluno);
        return "aluno/form";
    }

    @Data
    public static class PesquisarRequest {
        private String filtro;
    }

}
