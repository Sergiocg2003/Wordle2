package com.Wordle2.wordle2.controlador;

import com.Wordle2.wordle2.servicio.PalabraService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class PalabraController {
    private PalabraService palabraService;

    @GetMapping("palabras/aleatorias/{cantidad}")
    public ResponseEntity<Object> getPalabrasAleatorias(@PathVariable Long cantidad) throws Exception{
        if(cantidad < 1){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        else{
            List<String> listaPalabrasAleatorias = palabraService.palabrasRandom(cantidad);
            return ResponseEntity.ok(listaPalabrasAleatorias);
        }
    }

    @GetMapping("palabras/empiezan/{cadena}")
    public ResponseEntity<Object> getPalabrasEmpiezan(@PathVariable String cadena) throws Exception{
        if(cadena.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        else{
            List<String> listaPalabrasEmpiezan = palabraService.palabrasEmpiezan(cadena);
            if(listaPalabrasEmpiezan.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            else{
                return ResponseEntity.ok(listaPalabrasEmpiezan);
            }
        }
    }

    @GetMapping("palabras/terminan/{cadena}")
    public ResponseEntity<Object> getPalabrasTerminan(@PathVariable String cadena) throws Exception{
        if(cadena.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        else{
            List<String> listaPalabrasTerminan = palabraService.palabrasTerminan(cadena);
            if(listaPalabrasTerminan.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            else{
                return ResponseEntity.ok(listaPalabrasTerminan);
            }
        }
    }


}
