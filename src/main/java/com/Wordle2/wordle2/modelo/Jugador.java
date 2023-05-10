package com.Wordle2.wordle2.modelo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "jugador")
public class Jugador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idJugador")
    private Integer idJugador;

    @Column(name="Nombre", length = 45, nullable = false)
    private String nombre;

    @Column(name="Imagen", length = 45)
    private String imagen = "";

    @Column(name= "Pin", length = 45, nullable = false)
    private String pin;

    @Column(name="Puntos", nullable = false)
    private Integer puntos = 0;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "equipo_idEquipo")
    private Equipo equipo;
}
