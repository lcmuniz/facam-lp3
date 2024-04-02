package com.acme.filmesapi.repositorio;

import com.acme.filmesapi.entidade.Genero;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GeneroRepository extends JpaRepository<Genero, Long> {
    List<Genero> findAllByNomeLikeIgnoreCase(String filtro);
}
