package com.Wordle2.wordle2.modelo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "juego")
public class Juego {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idJuego")
    private Integer idJuego;

    @Column(name="Nombre", length = 45, nullable = false)
    private String nombre;

    @Column(name="Dificultad", length = 45, nullable = false)
    private String dificultad;

    @Column(name="Intentos", nullable = false)
    private Integer intentos;

    @Column(name="PtosMax", nullable = false)
    private Integer ptosMax;

    @Column(name="Instrucciones", length = 45, nullable = false)
    private String instrucciones;

    @OneToMany(mappedBy = "juego", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private List<Partida> partidas;
}
