package com.example.demo.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping
    public String home(Model model, HttpSession session) {
        var email = session.getAttribute("email");
        model.addAttribute("email", email);
        model.addAttribute("sessionId", session.getId());
        return "home";
    }

}
