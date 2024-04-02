package com.acme.filmesapi.controller;

import com.acme.filmesapi.entidade.Filme;
import com.acme.filmesapi.repositorio.FilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/filmes")
public class FilmeController {

    @Autowired
    FilmeRepository filmeRepository;

    @GetMapping
    public List<Filme> listarFilmes() {
        return filmeRepository.findAll();
    }

    @PostMapping
    public Filme criarFilme(@RequestBody Filme filme) {
        return filmeRepository.save(filme);
    }

    @DeleteMapping("/{id}")
    public void deletarFilme(@PathVariable Long id) {
        filmeRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public Filme atualizarFilme(@PathVariable Long id, @RequestBody Filme filmeAtualizado) {
        return filmeRepository.findById(id)
                .map(filme -> filmeRepository.save(atualizarCampos(filme, filmeAtualizado)))
                .orElseThrow(() -> new RuntimeException("Filme não encontrado"));
    }

    @GetMapping("/buscar")
    public List<Filme> buscar(@RequestParam MultiValueMap<String, String> params) {
        String titulo = params.getFirst("titulo");
        String diretor = params.getFirst("diretor");
        String genero = params.getFirst("genero");
        Integer anoLancamento = params.getFirst("anoLancamento") != null ? Integer.parseInt(params.getFirst("anoLancamento")): null;
        String sinopse = params.getFirst("sinopse");
        Integer avaliacao = params.getFirst("avaliacao") != null ? Integer.parseInt(params.getFirst("avaliacao")) : null;

        return  filmeRepository.buscar(titulo, diretor, genero, anoLancamento, sinopse, avaliacao);

    }

    private Filme atualizarCampos(Filme filme, Filme filmeAtualizado) {
        if(filmeAtualizado.getTitulo() != null) filme.setTitulo(filmeAtualizado.getTitulo());
        if(filmeAtualizado.getDiretor() != null) filme.setDiretor(filmeAtualizado.getDiretor());
        if(filmeAtualizado.getAnoLancamento() != null) filme.setAnoLancamento(filmeAtualizado.getAnoLancamento());
        if(filmeAtualizado.getGenero() != null) filme.setGenero(filmeAtualizado.getGenero());
        if(filmeAtualizado.getSinopse() != null) filme.setSinopse(filmeAtualizado.getSinopse());
        if(filmeAtualizado.getAvaliacao() != null) filme.setAvaliacao(filmeAtualizado.getAvaliacao());
        if(filmeAtualizado.getDataCadastro() != null) filme.setDataCadastro(filmeAtualizado.getDataCadastro());
        return filme;
    }

}
