package com.Wordle2.wordle2.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class DTOPartida {
    private Integer idPartida;
    private LocalDateTime fecha;
    private Integer puntos;
    private String palabra;
    private Integer intentos;
    private Integer idJugador;
    private Integer idJuego;

}
