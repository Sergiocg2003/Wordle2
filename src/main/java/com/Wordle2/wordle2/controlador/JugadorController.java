package com.Wordle2.wordle2.controlador;

import com.Wordle2.wordle2.dto.DTOJugador;
import com.Wordle2.wordle2.dto.DTOJugadorModif;
import com.Wordle2.wordle2.dto.converter.DTOJugadorConverter;
import com.Wordle2.wordle2.modelo.Equipo;
import com.Wordle2.wordle2.modelo.Jugador;
import com.Wordle2.wordle2.repositorio.EquipoRepo;
import com.Wordle2.wordle2.repositorio.JugadorRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
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
            return ResponseEntity.notFound().build();
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
            return ResponseEntity.notFound().build();
        }
        else{
            System.out.println(jugadorBuscado.get().getEquipo());
            return ResponseEntity.ok(jugadorBuscado);
        }
    }

    @DeleteMapping("jugador/{id}")
    public ResponseEntity<?> deleteJugadorById(@PathVariable Integer id){
        Optional<Jugador> jugadorBuscado = jugadorRepo.findById(id);
        if(jugadorBuscado.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        else{
            jugadorRepo.deleteById(id);
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping("/jugador")
    public ResponseEntity<?> createJugador(@RequestBody DTOJugadorModif newJugador){
        List<Jugador> jugadorExiste = jugadorRepo.findByNombre(newJugador.getNombre());
        if(!jugadorExiste.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        else{
            Jugador jugadorNuevo = new Jugador();
            jugadorNuevo.setNombre(newJugador.getNombre());
            jugadorNuevo.setPin(newJugador.getPin());
            jugadorNuevo.setPuntos(0);
            jugadorNuevo.setImagen("");
            Optional<Equipo> equipo = equipoRepo.findById(newJugador.getEquipo_idEquipo());
            jugadorNuevo.setEquipo(equipo.get());
            jugadorRepo.save(jugadorNuevo);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
    }

    @PutMapping("/jugador/{id}")
    public ResponseEntity<?> updateJugador(@RequestBody DTOJugadorModif newJugador, @PathVariable Integer id){
        Optional<Jugador> jugadorBuscado = jugadorRepo.findById(id);
        List<Jugador> jugadorExiste = jugadorRepo.findByNombre(newJugador.getNombre());
        if(jugadorBuscado.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        else if (!jugadorExiste.isEmpty() && !Objects.equals(jugadorBuscado.get().getNombre(), newJugador.getNombre())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        else{
            if(newJugador.getNombre() != null && !newJugador.getNombre().isEmpty()){
                jugadorBuscado.get().setNombre(newJugador.getNombre());
            }
            if(newJugador.getPin() != null && !newJugador.getPin().isEmpty()){
                jugadorBuscado.get().setPin(newJugador.getPin());
            }
            if(newJugador.getImagen() != null && !newJugador.getImagen().isEmpty()){
                jugadorBuscado.get().setImagen(newJugador.getImagen());
            }
            if(newJugador.getEquipo_idEquipo() != null && newJugador.getEquipo_idEquipo() > 0) {
                Equipo equipoNuevo = equipoRepo.findById(newJugador.getEquipo_idEquipo()).orElse(null);
                jugadorBuscado.get().setEquipo(equipoNuevo == null ? jugadorBuscado.get().getEquipo() : equipoNuevo);
            }
            Jugador jugador = jugadorBuscado.get();
            jugadorRepo.save(jugador);
            return ResponseEntity.ok(jugador);
        }
    }
}
