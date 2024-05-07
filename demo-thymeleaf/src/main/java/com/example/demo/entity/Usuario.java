package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "usuarios")
@Data
public class Usuario {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer usuarioId;
    private String email;
    private String senhaCriptografada;
}
