package com.Wordle2.wordle2.controlador;

import com.Wordle2.wordle2.modelo.Equipo;
import com.Wordle2.wordle2.repositorio.EquipoRepo;
import com.Wordle2.wordle2.repositorio.JugadorRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class EquipoController {
    private final EquipoRepo equipoRepo;
    private final JugadorRepo jugadorRepo;

    @GetMapping("/equipos")
    public ResponseEntity<Object> getAllEquipos(){
        List<Equipo> equipos = equipoRepo.findAll();
        if(equipos.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        else{
            return ResponseEntity.ok(equipos);
        }
    }

    @GetMapping("/equipo/{id}")
    public ResponseEntity<Object> getEquipo(@PathVariable Integer id){
        Optional<Equipo> equipoBuscado = equipoRepo.findById(id);
        if(equipoBuscado.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        else{
            return ResponseEntity.ok(equipoBuscado);
        }
    }

    @DeleteMapping("/equipo/{id}")
    public ResponseEntity<?> deleteEquipoById(@PathVariable Integer id){
        Optional<Equipo> equipoBuscado = equipoRepo.findById(id);
        if(equipoBuscado.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        else{
            equipoRepo.delete(equipoBuscado.get());
            return ResponseEntity.noContent().build();
        }
    }

    /*@PostMapping("/equipo")
    public ResponseEntity<?> createEquipo(@RequestBody Equipo newEquipo){
        List<Equipo> equipoExiste = equipoRepo.findByNombre(newEquipo.getNombre());
        if(!equipoExiste.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        else{
            newEquipo.setImagen("");
            newEquipo.setPuntos(0);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
    }

    @PutMapping("/equipo/{id}")
    public ResponseEntity<?> modifyEquipo(@PathVariable Integer id, @RequestBody Equipo newEquipo){
        Optional<Equipo> equipoBuscado = equipoRepo.findById(id);
        List<Equipo> equipoExiste = equipoRepo.findByNombre(newEquipo.getNombre());
        if(equipoBuscado.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        else if(!equipoExiste.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        else{
            if(newEquipo.getNombre() != null && !newEquipo.getNombre().isEmpty() && !newEquipo.getNombre().equals(equipoBuscado.get().getNombre())){
                equipoBuscado.get().setNombre(newEquipo.getNombre());
            }
            if(newEquipo.getImagen() != null && !newEquipo.getImagen().equals(equipoBuscado.get().getImagen())){
                equipoBuscado.get().setImagen(newEquipo.getImagen());
            }
            return ResponseEntity.status(HttpStatus.OK).build();
        }
    }*/
}
