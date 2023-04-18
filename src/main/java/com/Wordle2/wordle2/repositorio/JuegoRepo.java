package com.Wordle2.wordle2.repositorio;

import com.Wordle2.wordle2.modelo.Juego;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JuegoRepo extends JpaRepository<Juego, Integer> {
    List<Juego> findByNombre(String nombre);
}
