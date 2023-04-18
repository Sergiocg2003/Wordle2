package com.Wordle2.wordle2.repositorio;

import com.Wordle2.wordle2.modelo.Partida;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PartidaRepo extends JpaRepository<Partida, Integer> {
    List<Partida> findByJugador(Integer jugador_idJugador);
}
