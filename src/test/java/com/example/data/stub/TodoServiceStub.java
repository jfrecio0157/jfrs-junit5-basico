package com.example.data.stub;

import com.example.data.api.TodoService;

import java.util.Arrays;
import java.util.List;

public class TodoServiceStub implements TodoService {
    @Override
    public List<String> retrieveTodos(String user) {
        return Arrays.asList("Learn Spring", "Learn Java", "Learn Spring Boot");
    }

    @Override
    public void deleteTodo(String todo) {

    }
}
