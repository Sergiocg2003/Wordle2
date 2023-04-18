package com.Wordle2.wordle2.controlador;

import com.Wordle2.wordle2.modelo.Equipo;
import com.Wordle2.wordle2.modelo.Juego;
import com.Wordle2.wordle2.repositorio.EquipoRepo;
import com.Wordle2.wordle2.repositorio.JugadorRepo;
import com.Wordle2.wordle2.repositorio.PartidaRepo;
import com.Wordle2.wordle2.repositorio.JuegoRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class JuegoController {
    private final EquipoRepo equipoRepo;
    private final JugadorRepo jugadorRepo;
    private final PartidaRepo partidaRepo;
    private final JuegoRepo juegoRepo;

    @GetMapping("/juegos")
    public ResponseEntity<Object> getAllJuegos(){
        List<Juego> juegos = juegoRepo.findAll();
        if(juegos.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        else{
            return ResponseEntity.ok(juegos);
        }
    }

    @GetMapping("/juego/{nombre}")
    public ResponseEntity<Object> getJuego(@PathVariable String nombre){
        List<Juego> juegoBuscado = juegoRepo.findByNombre(nombre);
        if(juegoBuscado.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        else{
            return ResponseEntity.ok(juegoBuscado);
        }
    }
}
