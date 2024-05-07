package com.acme.facamsa.service;

import com.acme.facamsa.entity.Aluno;
import com.acme.facamsa.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository repository;

    public List<Aluno> obterAlunos() {
        return repository.findAll();
    }

    public List<Aluno> pesquisar(String filtro) {
        return repository.pesquisar("%" + filtro.trim() + "%");
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }

}
