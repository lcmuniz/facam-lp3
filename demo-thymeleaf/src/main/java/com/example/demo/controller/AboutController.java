package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AboutController {

    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("nome", "Pedro Silva e Sousa");
        model.addAttribute("email", "pedro@gmail.com");
        model.addAttribute("data",
                LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        model.addAttribute("idade", 12);


        List<String> cidades = new ArrayList<>();
        cidades.add("São Luís");
        cidades.add("Fortaleza");
        cidades.add("Teresina");

        model.addAttribute("cidades", cidades);

        return "about";
    }

}
