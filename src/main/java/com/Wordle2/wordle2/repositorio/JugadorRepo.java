package com.Wordle2.wordle2.repositorio;

import com.Wordle2.wordle2.modelo.Jugador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JugadorRepo extends JpaRepository<Jugador, Integer> {
    List<Jugador> findByNombreEqualsIgnoreCase(String Nombre);
}