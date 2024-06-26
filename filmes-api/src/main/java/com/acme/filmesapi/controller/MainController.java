package com.acme.filmesapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
public class MainController {

    @GetMapping
    public String main() {
        return LocalDateTime.now().toString();
    }

}
