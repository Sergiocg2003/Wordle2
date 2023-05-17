package com.Wordle2.wordle2.servicio;

import org.springframework.stereotype.Service;
import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
@Service
public class PalabraService {
    private List<String> listaPalabras = new ArrayList<>();
    public PalabraService() {
        ClassPathResource resource = new ClassPathResource("palabras.txt");
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()));
            String line;
            while((line = reader.readLine()) != null){
                listaPalabras.add(line.trim());
            }
            reader.close();
        } catch (IOException ioe){
            throw new RuntimeException("No existe el fichero");
        }
    }

    public List<String> palabrasRandom(Long numero) throws IOException{
        List<String> listaRandom = new ArrayList<>();
        if(numero > listaPalabras.size()){
            numero = (long) listaPalabras.size();
        }
        for(int x = 0; x < numero; x++){
            int random = (int) (Math.random() * listaPalabras.size());
            listaRandom.add(listaPalabras.get(random));
        }
        return listaRandom;
    }

    public List<String> palabrasEmpiezan(String cadena) throws IOException{
        List<String> listaEmpiezan = new ArrayList<>();
        for(int x = 0; x < listaPalabras.size(); x++){
            if(listaPalabras.get(x).startsWith(cadena)){
                listaEmpiezan.add(listaPalabras.get(x));
            }
        }
        return listaEmpiezan;
    }

    public List<String> palabrasTerminan(String cadena) throws IOException{
        List<String> listaTerminan = new ArrayList<>();
        for(int x = 0; x < listaPalabras.size(); x++){
            if(listaPalabras.get(x).endsWith(cadena)){
                listaTerminan.add(listaPalabras.get(x));
            }
        }
        return listaTerminan;
    }

}
