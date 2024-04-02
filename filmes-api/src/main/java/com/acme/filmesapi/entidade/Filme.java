package com.acme.filmesapi.entidade;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "filmes")
public class Filme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long filmeId;
    private String titulo;
    private String diretor;
    private Integer anoLancamento;
    @ManyToOne
    @JoinColumn(name = "generoId")
    private Genero genero;
    private String sinopse;
    private Integer avaliacao;
    private LocalDate dataCadastro;
}
