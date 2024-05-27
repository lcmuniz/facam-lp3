package com.acme.filmesapi.model;

import com.acme.filmesapi.DadosTemporada;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.val;

import java.util.ArrayList;
import java.util.List;

@Data
public class Temporada {
        private String serie;
        private String titulo;
        private Integer numeroTemporada;
        private List<Episodio> episodios  = new ArrayList<>();


        public static Temporada from(String jsonTemporada, Serie serie) throws JsonProcessingException {
                val mapper = new ObjectMapper();
                val dadosTemporada = mapper.readValue(jsonTemporada, DadosTemporada.class);
                val temporada = new Temporada();
                temporada.setTitulo(dadosTemporada.titulo());
                temporada.setNumeroTemporada(Integer.valueOf(dadosTemporada.numeroTemporada()));
                temporada.setSerie(serie.getTitulo());

                val episodios = dadosTemporada.episodios().stream()
                        .map(dadosEpisodio -> Episodio.from(dadosEpisodio, temporada))
                        .toList();
                temporada.getEpisodios().addAll(episodios);
                return temporada;
        }

}
