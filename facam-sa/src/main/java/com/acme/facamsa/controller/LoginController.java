package com.acme.facamsa.controller;

import com.acme.facamsa.entity.Usuario;
import com.acme.facamsa.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private UsuarioService service;

    @PostMapping("/login")
    public String doLogin(HttpSession session, @ModelAttribute DoLoginRequest request ) {
        var opt = service.login(request.email, request.senha);
        session.setAttribute("usuario", opt.orElse(null));
        if (opt.isEmpty()) {
            session.setAttribute("mensagem", "Usuário ou senha inválidos");
        }
        return "redirect:/";
    }

    @Data
    public static class DoLoginRequest {
        private String email;
        private String senha;
    }

}
