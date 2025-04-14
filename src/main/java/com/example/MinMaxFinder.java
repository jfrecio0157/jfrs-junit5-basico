package com.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MinMaxFinder {
    public List<Integer> findMinMax (List<Integer> numeros){

        List<Integer> lista = new ArrayList<>();
        //Se comprueba que no sea nula ni vacia
        if ( numeros == null || numeros.isEmpty()){
            return lista;
        }

        Integer min = Collections.min(numeros);
        Integer max = Collections.max(numeros);
        lista.add(min);
        lista.add(max);

        return lista;
    }
}
