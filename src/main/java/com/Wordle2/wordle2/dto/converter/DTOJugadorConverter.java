package com.Wordle2.wordle2.dto.converter;

import com.Wordle2.wordle2.dto.DTOJugador;
import com.Wordle2.wordle2.modelo.Jugador;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DTOJugadorConverter {
    private final ModelMapper modelMapper;
    public DTOJugador convertToDTO(Jugador jugador){
        return modelMapper.map(jugador,DTOJugador.class);
    }
}
