package com.acme.filmesapi.repositorio;

import com.acme.filmesapi.entidade.Filme;
import com.acme.filmesapi.entidade.Genero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FilmeRepository extends JpaRepository<Filme, Long> {
    @Query(nativeQuery = true, value =
            """
            select f.* from filmes f
            where 
            (:titulo is null or lower(f.titulo) like lower('%' || :titulo || '%'))
            and (:diretor is null or lower(f.diretor) like lower('%' || :diretor || '%'))
            and (:anoLancamento is null or f.ano_lancamento = :anoLancamento)
            and (:sinopse is null or lower(f.sinopse) like lower('%' || :sinopse || '%'))
            and (:avaliacao is null or f.avaliacao = :avaliacao)
            and (:genero is null or exists (select 1 from generos g where g.genero_id = f.genero_id and lower(g.nome) like lower('%' || :genero || '%')))
            """
    )
    List<Filme> buscar(String titulo, String diretor, String genero, Integer anoLancamento, String sinopse, Integer avaliacao);
}

