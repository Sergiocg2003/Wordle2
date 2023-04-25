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
    public DTOPartida convertToDTO(Partida partida){return modelMapper.map(partida,DTOPartida.class);}
}
