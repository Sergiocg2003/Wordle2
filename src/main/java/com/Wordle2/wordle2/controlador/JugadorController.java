package com.Wordle2.wordle2.controlador;

import com.Wordle2.wordle2.dto.DTOJugador;
import com.Wordle2.wordle2.dto.converter.DTOJugadorConverter;
import com.Wordle2.wordle2.modelo.Jugador;
import com.Wordle2.wordle2.repositorio.EquipoRepo;
import com.Wordle2.wordle2.repositorio.JugadorRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class JugadorController {
    private final JugadorRepo jugadorRepo;
    private final DTOJugadorConverter dtoJugadorConverter;
    private final EquipoRepo equipoRepo;

    @GetMapping("/jugadores")
    public ResponseEntity<List<?>> getAllJugadores(){
        List<Jugador> jugadores = jugadorRepo.findAll();
        if(jugadores.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        else{
            List<DTOJugador> DTOJugadorList = jugadores.stream().map(dtoJugadorConverter::convertToDTO).collect(Collectors.toList());
            return ResponseEntity.ok(DTOJugadorList);
        }
    }

    @GetMapping("/jugador/{id}")
    public ResponseEntity<Object> getJugadorById(@PathVariable Integer id){
        Optional<Jugador> jugadorBuscado = jugadorRepo.findById(id);
        if(jugadorBuscado.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        else{
            System.out.println(jugadorBuscado.get().getEquipo());
            return ResponseEntity.status(HttpStatus.OK).build();
        }
    }

}
