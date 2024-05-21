package com.acme.demo5.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Temporada {
        private String serie;
        private String titulo;
        private Integer numeroTemporada;
        private List<Episodio> episodios  = new ArrayList<>();
}
