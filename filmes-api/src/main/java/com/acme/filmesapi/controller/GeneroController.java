package com.acme.filmesapi.controller;

import com.acme.filmesapi.entidade.Genero;
import com.acme.filmesapi.repositorio.GeneroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/generos")
public class GeneroController {

    @Autowired
    private GeneroRepository generoRepository;

    @GetMapping
    public List<Genero> listarGeneros() {
        return  generoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Genero> obterGenero(@PathVariable Long id) {
        return generoRepository.findById(id);
    }

    @PostMapping
    public void post(@RequestBody Genero genero) {
        generoRepository.save(genero);
    }

    @PutMapping("/{id}")
    public Genero atualizarGenero(@PathVariable Long id, @RequestBody Genero genero) {
        Optional<Genero> o = generoRepository.findById(id);
        if (o.isEmpty()) {
            throw new RuntimeException("Genero nao existe");
        }
        Genero generoBanco = o.get();
        generoBanco.setNome(genero.getNome());
        return generoRepository.save(generoBanco);
    }

    @DeleteMapping("/{id}")
    public void deletarGenero(@PathVariable Long id) {
        generoRepository.deleteById(id);
    }
    @GetMapping("/buscar")
    public List<Genero> buscar(@RequestParam String nome) {
        return generoRepository.findAllByNomeLikeIgnoreCase("%" + nome.trim() + "%");
    }
}
