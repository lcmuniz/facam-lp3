package com.acme.demo5.model;

import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class Serie {
    private String titulo;
    private LocalDate dataLancamento;
    private String genero;
    private Double avaliacao;
    private List<Temporada> temporadas = new ArrayList<>();
}
