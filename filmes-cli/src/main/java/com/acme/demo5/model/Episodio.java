package com.acme.demo5.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Episodio {
        private Integer numeroTemporada;
        private String titulo;
        private LocalDate dataLancamento;
        private Integer numeroEpisodio;
        private Double avaliacao;
}
