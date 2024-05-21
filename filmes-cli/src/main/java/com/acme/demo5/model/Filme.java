package com.acme.demo5.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Filme {
    private String titulo;
    private LocalDate dataLancamento;
    private String genero;
    private Double avaliacao;
}
