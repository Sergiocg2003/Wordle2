package com.Wordle2.wordle2.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "equipo")
public class Equipo {


    @Column(name="Nombre", length = 45, nullable = false)
    private String nombre;

    @Column(name="Imagen", length = 45)
    private String imagen = "";

    @Column(name="Puntos", nullable = false)
    private Integer puntos = 0;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idEquipo")
    private Integer idEquipo;
}
