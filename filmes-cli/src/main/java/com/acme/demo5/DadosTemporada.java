package com.acme.demo5;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosTemporada(
        @JsonAlias("Title")
        String titulo,
        @JsonAlias("Season")
        String numeroTemporada,
        @JsonAlias("Episodes")
        List<DadosEpisodio> episodios
        ) {
}
