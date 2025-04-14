package com.example.business;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ListTest {

    @Mock
    private  List listMock; //List listMock = mock(List.class);

    //Inicialización de los mocks
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void test(){
        when(listMock.size()).thenReturn(2);
        assertEquals(2,listMock.size());
    }

    @Test
    void testGet(){
        when (listMock.get(anyInt())).thenReturn("Prueba");
        assertEquals("Prueba",listMock.get(anyInt()));

    }

    @Test
    void testException(){
        when (listMock.get(anyInt())).thenThrow(new RuntimeException("Something"));

        assertThrows(RuntimeException.class,
                () -> {listMock.get(0);
        });



    }
}
