package com.acme.facamsa.controller;

import com.acme.facamsa.entity.Usuario;
import com.acme.facamsa.service.AlunoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Autowired
    private AlunoService alunoService;

    @GetMapping
    public String main(HttpSession session, Model model) {
        if (session.getAttribute("usuario") == null) {
            model.addAttribute("mensagem", session.getAttribute("mensagem"));
            model.addAttribute("usuario", new Usuario());
            return "login";
        }

        var alunos = alunoService.obterAlunos();
        model.addAttribute("alunos", alunos);
        model.addAttribute("request", new AlunoController.PesquisarRequest());
        return "aluno/index";
    }

}
