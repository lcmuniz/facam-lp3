package com.example.demo.controller;

import com.example.demo.repository.UsuarioRepository;
import com.example.demo.request.UsuarioRequest;
import com.example.demo.util.MD5Util;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/login")
    public String login(Model model, HttpSession session) {
        var request = new UsuarioRequest();
        var mensagem = session.getAttribute("mensagem");
        session.setAttribute("mensagem", "");

        model.addAttribute("request", request);
        model.addAttribute("mensagem", mensagem);

        return "login";
    }

    @PostMapping("/do-login")
    public String doLogin(@ModelAttribute UsuarioRequest request, Model model, HttpSession session) {
        var email = request.getEmail();
        var senha = request.getSenha();

        var senhaCriptografada = MD5Util.getMD5(senha);

        var opt = usuarioRepository.findFirstByEmail(email);

        if (opt.isEmpty()) {
            session.setAttribute("mensagem", "Usuário ou senha inválidos");
            return "redirect:/login";
        }

        var usuario = opt.get();

        if (!usuario.getSenhaCriptografada().equals(senhaCriptografada)) {
            session.setAttribute("mensagem", "Usuário ou senha inválidos");
            return "redirect:/login";
        }

        session.setAttribute("email", email);

        return "redirect:/";
    }

}
