package com.example.business;

import com.example.data.api.TodoService;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class TodoBusinessImplMockitoTest {

    private List<String> todos = new ArrayList<>();

    //@Mock crea un mock de TodoService.
    @Mock
    //Equivale a TodoService todoService = mock(TodoService.class);
    private TodoService todoService;

    //@InjectMocks crea una instancia de TodoBusinessImpl e inyecta el mock todoService.
    @InjectMocks
    //Equivale a TodoBusinessImpl todoBusinessImpl = new todoBusinessImpl(todoService);
    private TodoBusinessImpl todoBusinessImpl;


    @Captor
    //Para capturar argumentos de tipo String
    //ArgumentCaptor<String> argumentCaptorString = ArgumentCaptor.forClass(String.class);
    ArgumentCaptor<String> argumentCaptorString;

    //Inicialización de los mocks
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        todos = Arrays.asList("Learn Spring", "Learn Java", "Learn Spring Boot","Learn Dance");
    }

    @Test
    @DisplayName("Test with two elements")
    public void testRetrieveTodosRelatedToSpring() {
        //List<String> todos = Arrays.asList("Learn Spring", "Learn Java", "Learn Spring Boot");

        when(todoService.retrieveTodos(anyString())).thenReturn(todos);

        List<String> filteredTodos = todoBusinessImpl.retrieveTodosRelatedToSpring(anyString());

        assertEquals(2, filteredTodos.size());
        assertEquals("Learn Spring", filteredTodos.get(0));
        assertEquals("Learn Spring Boot", filteredTodos.get(1));
    }

    @Test
    @DisplayName("Test when List is empty")
    public void testRetrieveTodosRelatedToSpring_Empty() {
        List<String> todos = List.of();

        when(todoService.retrieveTodos(anyString())).thenReturn(todos);

        List<String> filteredTodos = todoBusinessImpl.retrieveTodosRelatedToSpring(anyString());

        assertEquals(0, filteredTodos.size());
    }

    /* Test usando el estilo BDD
    BDD Estilo:
        Given: Configuramos el comportamiento del mock usando given.
        When: Ejecutamos el método bajo prueba.
        Then: Verificamos los resultados y las interacciones con el mock usando then.
     */
    @Test
    @DisplayName("Test using BDD two elements")
    public void testUsingBDD() {
        //given
        given(todoService.retrieveTodos(anyString())).willReturn(todos);

        //when
        List<String> filteredTodos = todoBusinessImpl.retrieveTodosRelatedToSpring(anyString());

        //then
        assertEquals(2, filteredTodos.size());
        assertEquals("Learn Spring", filteredTodos.get(0));
        assertEquals("Learn Spring Boot", filteredTodos.get(1));
        then(todoService).should().retrieveTodos(anyString());
    }

    @Test
    @DisplayName("Test with two elements using BDD")
    public void testRetrieveTodosRelatedToSpringUsingBDD() {
        //given
        //List<String> todos = Arrays.asList("Learn Spring", "Learn Java", "Learn Spring Boot");

        //when
        given(todoService.retrieveTodos(anyString())).willReturn(todos);

        List<String> filteredTodos = todoBusinessImpl.retrieveTodosRelatedToSpring(anyString());

        //then
        assertThat(filteredTodos.size(), is(2));
        assertThat(filteredTodos.get(0), is("Learn Spring"));
        assertThat(filteredTodos.get(1), is("Learn Spring Boot"));
    }


    @Test
    public void testDelete(){
        when(todoService.retrieveTodos(anyString())).thenReturn(todos);

        todoBusinessImpl.deleteTodosNoRelatedToSpring(anyString());

        //Se usa para verificar que el método deleteTodo del mock todoService
        //fue llamado con el argumento "Learn Java".
        verify(todoService).deleteTodo("Learn Java");

        //Se usa para verificar que el método deleteTodo del mock todoService
        //nunca fue llamado con el argumento "Learn Spring".
        verify(todoService, Mockito.never()).deleteTodo(todos.get(0));

        //se usa para verificar que el método deleteTodo del mock todoService
        // fue llamado exactamente una vez con el argumento "Learn Java"
        verify(todoService, Mockito.times(1)).deleteTodo("Learn Java");

        //se usa para verificar que el método deleteTodo del mock todoService
        // fue llamado al menos una vez con el argumento "Learn Dance"
        verify(todoService, Mockito.atLeastOnce()).deleteTodo("Learn Dance");
    }

    @Test
    @DisplayName("Verificar una lista con mockito y BDD")
    public void testLisByMockAndBdd(){
        //given
        List<String> lista = mock(List.class);

        given(lista.get(anyInt())).willReturn("Primer elemento");

        //when
        String firstElement = lista.get(0);

        //then
        assertThat(firstElement, is("Primer elemento"));

    }

    @Test
    @DisplayName("Verificar el funcionamiento de ArgumentCaptor")
    public void testCaptureArgument (){

        when(todoService.retrieveTodos(anyString())).thenReturn(todos);

        todoBusinessImpl.deleteTodosNoRelatedToSpring(anyString());

        //Se usa para verificar que el método deleteTodo del mock todoService
        //fue llamado con dos argumentos
        verify(todoService,Mockito.times(2)).deleteTodo(argumentCaptorString.capture());

        //Lista de los parametros con los que se llama a deleteTodo
        List<String> valoresDevueltos = argumentCaptorString.getAllValues();
        assertEquals(2, valoresDevueltos.size());
        assertTrue(valoresDevueltos.contains("Learn Dance"));
        assertTrue(valoresDevueltos.contains("Learn Java"));

    }
}

