package com.Wordle2.wordle2.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DTOJugador {
    private Integer idJugador;
    private Integer idEquipo;
    private String Nombre;
    private String Imagen;
    private String Pin;
    private Integer Puntos;
}
