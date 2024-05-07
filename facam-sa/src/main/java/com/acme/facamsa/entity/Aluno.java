package com.acme.facamsa.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "alunos")
public class Aluno {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long alunoId;
    private String nome;
    private String email;
    private String celular;
}
