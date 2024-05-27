package com.acme.filmesapi.model;

import com.acme.filmesapi.DadosSerie;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.val;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Data
public class Serie {
    private String titulo;
    private LocalDate dataLancamento;
    private String genero;
    private Double avaliacao;
    private Integer numeroTemporadas;
    private List<Temporada> temporadas = new ArrayList<>();

    public static Serie from(String jsonSerie) throws JsonProcessingException {
        val mapper = new ObjectMapper();
        val formatter = DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.forLanguageTag("pt_BR"));

        val dadosSerie = mapper.readValue(jsonSerie, DadosSerie.class);

        val serie = new Serie();
        serie.setTitulo(dadosSerie.titulo());
        serie.setGenero(dadosSerie.genero());
        serie.setAvaliacao(Double.valueOf(dadosSerie.avaliacao()));
        serie.setDataLancamento(LocalDate.parse(dadosSerie.dataLancamento(), formatter));
        serie.setNumeroTemporadas(Integer.valueOf(dadosSerie.numeroTemporadas()));
        return serie;

    }
}
