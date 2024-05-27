package com.acme.filmesapi.model;

import com.acme.filmesapi.DadosEpisodio;
import lombok.Data;
import lombok.val;

import java.time.LocalDate;

@Data
public class Episodio {
        private Integer numeroTemporada;
        private String titulo;
        private LocalDate dataLancamento;
        private Integer numeroEpisodio;
        private Double avaliacao;

        public static Episodio from(DadosEpisodio dadosEpisodio, Temporada temporada) {
                val episodio = new Episodio();
                if (dadosEpisodio.avaliacao().equals("N/A")) {
                        episodio.setAvaliacao(null);
                }
                else {
                        episodio.setAvaliacao(Double.valueOf(dadosEpisodio.avaliacao()));
                }
                episodio.setNumeroEpisodio(Integer.valueOf(dadosEpisodio.numeroEpisodio()));
                if (dadosEpisodio.dataLancamento().equals("N/A")) {
                        episodio.setDataLancamento(null);
                }
                else {
                        episodio.setDataLancamento(LocalDate.parse(dadosEpisodio.dataLancamento()));
                }
                episodio.setTitulo(dadosEpisodio.titulo());
                episodio.setNumeroTemporada(temporada.getNumeroTemporada());
                return episodio;
        }
}
