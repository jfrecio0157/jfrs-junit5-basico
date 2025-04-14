package com.example.business;

import com.example.data.stub.TodoServiceStub;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;

public class TodoBusinessImplStubTest {

    @Test
    @DisplayName("Test de todoServiceStub")
    void usingTodoServiceStub(){
        TodoServiceStub todoServiceStub = new TodoServiceStub();
        TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceStub);

        List<String> todos = todoBusinessImpl.retrieveTodosRelatedToSpring("User");

        assertAll("Revisando todos",
                () -> Assertions.assertEquals(2, todos.size()),
                () -> Assertions.assertEquals("Learn Spring", todos.get(0)),
                () -> Assertions.assertEquals("Learn Spring Boot", todos.get(1))
        );

    }



}
