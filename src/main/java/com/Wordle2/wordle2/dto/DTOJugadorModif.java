package com.Wordle2.wordle2.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class DTOJugadorModif {
    private Integer idEquipo;
    private String Nombre;
    private String Imagen;
    private String Pin;
    private Integer Puntos;
}
