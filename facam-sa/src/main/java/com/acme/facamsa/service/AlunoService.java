package com.acme.facamsa.service;

import com.acme.facamsa.entity.Aluno;
import com.acme.facamsa.exception.AlunoNotFoundException;
import com.acme.facamsa.exception.AlunoNotValidException;
import com.acme.facamsa.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public void excluir(Long id) throws AlunoNotFoundException {
        var opt = repository.findById(id);
        if (opt.isEmpty()) {
            throw new AlunoNotFoundException("Aluno com id " + id + " não existe");
        }
        repository.deleteById(id);
    }

    public Aluno salvar(Aluno aluno) throws AlunoNotValidException {
        if (!ehValido(aluno)) {
            throw new AlunoNotValidException("Aluno não é válido");
        }
        return repository.save(aluno);
    }

    public Aluno obterAluno(Long id) throws AlunoNotFoundException {
        var opt =  repository.findById(id);
        if (opt.isEmpty()) {
            throw new AlunoNotFoundException("Aluno com id " + id + " não existe");
        }
        return opt.get();
    }

    private boolean ehValido(Aluno aluno) {
        if (aluno.getNome() == null || aluno.getNome().isEmpty() || aluno.getNome().length() <= 3) {
            return false;
        }
        if (aluno.getEmail() == null || aluno.getEmail().isEmpty()) {
            return false;
        }
        if (aluno.getCelular() == null || aluno.getCelular().isEmpty()) {
            return false;
        }
        return true;
    }

}
