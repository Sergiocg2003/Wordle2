package com.Wordle2.wordle2.dto.converter;

import com.Wordle2.wordle2.dto.DTOPartida;
import com.Wordle2.wordle2.modelo.Partida;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DTOPartidaConverter {
    private final ModelMapper modelMapper;
    public DTOPartida convertToDTO(Partida partida){
        DTOPartida dtoPartida = new DTOPartida();
        dtoPartida.setIdPartida(partida.getIdPartida());
        dtoPartida.setFecha(partida.getFecha());
        dtoPartida.setIntentos(partida.getIntentos());
        dtoPartida.setPuntos(partida.getPuntos());
        dtoPartida.setPalabra(partida.getPalabra());
        dtoPartida.setIdJuego(partida.getJuego().getIdJuego());
        dtoPartida.setIdJugador(partida.getJugador().getIdJugador());
        return dtoPartida;
    }
}
