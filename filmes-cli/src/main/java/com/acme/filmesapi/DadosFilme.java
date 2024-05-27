package com.acme.filmesapi;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosFilme(
        @JsonAlias("Title")
        String titulo,
        @JsonAlias("Released")
        String dataLancamento,
        @JsonAlias("Genre")
        String genero,
        @JsonAlias("imdbRating")
        String avaliacao) {
}
