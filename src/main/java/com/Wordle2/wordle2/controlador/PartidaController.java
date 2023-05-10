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

import java.time.LocalDateTime;
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
        System.out.println(id);
        List<Partida> partidasBuscadas = partidaRepo.findByJugador_idJugador(id);
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
            Partida partidaNueva = new Partida();
            partidaNueva.setFecha(LocalDateTime.now());
            partidaNueva.setPalabra(newPartida.getPalabra());
            partidaNueva.setPuntos(0);
            partidaNueva.setIntentos(0);
            Optional<Juego> juego = juegoRepo.findById(newPartida.getIdJuego());
            partidaNueva.setJuego(juego.get());
            Optional<Jugador> jugador = jugadorRepo.findById(newPartida.getIdJugador());
            partidaNueva.setJugador(jugador.get());
            partidaRepo.save(partidaNueva);
            return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/partida/{id}")
    public ResponseEntity<?> updatePartida(@RequestBody DTOPartidaModif newPartida, @PathVariable Integer id){
        System.out.println("Antes de partida buscada");
        Optional<Partida> partidaBuscada = partidaRepo.findById(id);
        System.out.println("Despues de buscar partida");
        Optional<Partida> partidaExiste = partidaRepo.findById(id);
        System.out.println("comprobar partida");
        if(partidaBuscada.isEmpty()){
            System.out.println("hola");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        else if (!partidaExiste.isEmpty() && !Objects.equals(partidaBuscada.get().getIdPartida(), id)){
            System.out.println("Adios");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        else{
            System.out.println("Estas aqui");
            if(newPartida.getPuntos() != null || newPartida.getPuntos() <= -1){
                System.out.println("1 if");
                partidaBuscada.get().setPuntos(newPartida.getPuntos());
            }
            if(newPartida.getIntentos() != null || newPartida.getIntentos() > 0){
                System.out.println("2 if");
                partidaBuscada.get().setIntentos(newPartida.getIntentos());
            }
            Partida partida = partidaBuscada.get();
            System.out.println(partida.getIdPartida());

            return ResponseEntity.ok(partida);
        }
    }
}
