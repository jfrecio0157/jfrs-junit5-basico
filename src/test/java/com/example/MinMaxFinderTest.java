package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MinMaxFinderTest {

    List<Integer> numerosPositivos = new ArrayList<>();
    List<Integer> numerosNegativos = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        numerosPositivos = Arrays.asList(2,56,87,12);
        numerosNegativos = Arrays.asList(-5,-3,-4,-9);
    }

    /*
    @Mock
    MinMaxFinder minMaxFinder;
    */

    @Test
    @DisplayName("Test busqueda Min y Max con Mock")
    public void testMinMaxFinderWithMock(){
        List<Integer> minMaxList;
        minMaxList = Arrays.asList(2,87);

        //En lugar de hacer la anotacion @Mock
        MinMaxFinder minMaxFinder = mock(MinMaxFinder.class);

        when (minMaxFinder.findMinMax(anyList())).thenReturn(minMaxList);

        assertEquals(2, minMaxList.get(0));
        assertEquals(87, minMaxList.get(1));
    }

    @Test
    @DisplayName("Test busqueda Min y Max sin Mock")
    public void testMinMaxFinderWithoutMock(){

        MinMaxFinder minMaxFinder = new MinMaxFinder();

        //Lista con datos positivos
        List<Integer> minMaxListPositivos = Arrays.asList(2,87);

        //Lista con datos negativos
        List<Integer> minMaxListNegativos = Arrays.asList(-9,-3);

        //Lista vacia
        List<Integer> vaciaList = List.of();

        //Lista nula
        List<Integer> nulaList = new ArrayList<>();

        assertAll("Test busqueda Min y Max sin Mock",
                () -> assertEquals(minMaxListPositivos, minMaxFinder.findMinMax(numerosPositivos), "No devuelve los valores min y max esperados"),
                () -> assertEquals(minMaxListNegativos, minMaxFinder.findMinMax(numerosNegativos), "No devuelve los valores min y max esperados"),
                () -> assertEquals(vaciaList, minMaxFinder.findMinMax(vaciaList),"Deberia devolver lista vacia"),
                () -> assertEquals(nulaList, minMaxFinder.findMinMax(null),"Deberia devolver lista nula")
        );
    }
}