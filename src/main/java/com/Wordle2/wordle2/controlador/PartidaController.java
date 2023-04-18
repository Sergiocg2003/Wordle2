package com.Wordle2.wordle2.controlador;

import com.Wordle2.wordle2.repositorio.EquipoRepo;
import com.Wordle2.wordle2.repositorio.JuegoRepo;
import com.Wordle2.wordle2.repositorio.JugadorRepo;
import com.Wordle2.wordle2.repositorio.PartidaRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PartidaController {
    private final EquipoRepo equipoRepo;
    private final JugadorRepo jugadorRepo;
    private final PartidaRepo partidaRepo;
    private final JuegoRepo juegoRepo;
}
