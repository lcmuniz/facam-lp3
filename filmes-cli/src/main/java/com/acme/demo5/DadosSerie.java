package com.acme.demo5;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosSerie(
        @JsonAlias("Title")
        String titulo,
        @JsonAlias("Released")
        String dataLancamento,
        @JsonAlias("Genre")
        String genero,
        @JsonAlias("totalSeasons")
        String numeroTemporadas,
        @JsonAlias("imdbRating")
        String avaliacao) {
}
