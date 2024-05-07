package com.acme.facamsa.repository;

import com.acme.facamsa.entity.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    @Query(value = "select a.* from alunos a where lower(a.nome) like lower(:filtro) or lower(a.email) like lower(:filtro) or lower(a.celular) like lower(:filtro)", nativeQuery = true)
    List<Aluno> pesquisar(String filtro);

}
