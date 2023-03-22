package com.Wordle2.wordle2.repositorio;

import com.Wordle2.wordle2.modelo.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EquipoRepo extends JpaRepository<Equipo, Integer> {
    List<Equipo> findByNombre(String nombre);
}