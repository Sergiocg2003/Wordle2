package com.Wordle2.wordle2.controlador;

import com.Wordle2.wordle2.dto.DTOPartida;
import com.Wordle2.wordle2.dto.DTOPartidaModif;
import com.Wordle2.wordle2.dto.converter.DTOPartidaConverter;
import com.Wordle2.wordle2.modelo.Juego;
import com.Wordle2.wordle2.modelo.Jugador;
import com.Wordle2.wordle2.modelo.Partida;
import com.Wordle2.wordle2.repositorio.EquipoRepo;
import com.Wordle2.wordle2.repositorio.JuegoRepo;
import com.Wordle2.wordle2.repositorio.JugadorRepo;
import com.Wordle2.wordle2.repositorio.PartidaRepo;
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
public class PartidaController {
    private final DTOPartidaConverter dtoPartidaConverter;
    private final EquipoRepo equipoRepo;
    private final JugadorRepo jugadorRepo;
    private final PartidaRepo partidaRepo;
    private final JuegoRepo juegoRepo;

    @GetMapping("/partidas")
    public ResponseEntity<List<?>> getAllPartidas(){
        List<Partida> partidas = partidaRepo.findAll();
        if(partidas.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        else{
            List<DTOPartida> DTOPartidaList = partidas.stream().map(dtoPartidaConverter::convertToDTO).collect(Collectors.toList());
            return ResponseEntity.ok(DTOPartidaList);
        }
    }

    @GetMapping("/partida/{id}")
    public ResponseEntity<List<?>> getPartidaById(@PathVariable Integer id){
        List<Partida> partidasBuscadas = partidaRepo.findByJugador(id);
        if(partidasBuscadas.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        else{
            List<DTOPartida> DTOPartidaList = partidasBuscadas.stream().map(dtoPartidaConverter::convertToDTO).collect(Collectors.toList());
            return ResponseEntity.ok(DTOPartidaList);
        }
    }

    @PostMapping("/partida")
    public ResponseEntity<?> createPartida(@RequestBody DTOPartida newPartida){
        Optional<Partida> partidaExiste = partidaRepo.findById(newPartida.getIdPartida());
        if(!partidaExiste.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        else{
            Partida partidaNueva = new Partida();
            partidaNueva.setFecha(newPartida.getFecha());
            partidaNueva.setPalabra(newPartida.getPalabra());
            partidaNueva.setPuntos(0);
            partidaNueva.setIntentos(newPartida.getIntentos());
            Optional<Juego> juego = juegoRepo.findById(newPartida.getJuego_idJuego());
            partidaNueva.setJuego(juego.get());
            Optional<Jugador> jugador = jugadorRepo.findById(newPartida.getJugador_idJugador());
            partidaNueva.setJugador(jugador.get());
            partidaRepo.save(partidaNueva);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
    }

    @PutMapping("/partida/{id}")
    public ResponseEntity<?> updatePartida(@RequestBody DTOPartidaModif newPartida, @PathVariable Integer id){
        Optional<Partida> partidaBuscada = partidaRepo.findById(id);
        Optional<Partida> partidaExiste = partidaRepo.findById(newPartida.getIdPartida());

        if(partidaBuscada.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        else if (!partidaExiste.isEmpty() && !Objects.equals(partidaBuscada.get().getIdPartida(), newPartida.getIdPartida())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        else{
            if(newPartida.getPuntos() != null || newPartida.getPuntos() <= -1){
                partidaBuscada.get().setPuntos(newPartida.getPuntos());
            }
            if(newPartida.getIntentos() != null || newPartida.getIntentos() > 0){
                partidaBuscada.get().setIntentos(newPartida.getIntentos());
            }
            Partida partida = partidaBuscada.get();
            partidaRepo.save(partida);
            return ResponseEntity.ok(partida);
        }
    }
}
