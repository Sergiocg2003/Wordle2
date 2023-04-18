package com.Wordle2.wordle2.modelo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "partida")
public class Partida {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idPartida")
    private Integer idPartida;

    @Column(name = "Fecha", nullable = false)
    private LocalDateTime fecha;

    @Column(name = "Puntos", nullable = false)
    private Integer puntos;

    @Column(name = "Palabra", length = 45, nullable = false)
    private String palabra;

    @Column(name = "Intentos", nullable = false)
    private Integer intentos;

    @ManyToOne
    @JoinColumn(name = "jugador_idJugador")
    @JsonBackReference
    private Jugador jugador;

    @ManyToOne
    @JoinColumn(name = "juego_idJuego")
    @JsonBackReference
    private Juego juego;
}
