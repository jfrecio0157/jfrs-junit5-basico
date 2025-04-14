package com.example.business;

import com.example.data.api.TodoService;

import java.util.ArrayList;
import java.util.List;

public class TodoBusinessImpl{
    //Se crea una instancia de la interface
    private TodoService todoService;

    //Constructor
    public TodoBusinessImpl(TodoService todoService) {
        this.todoService = todoService;
    }

    //Recorre la lista devuelta por todoService.retrieve y se queda solo con los que contienen la palabra "Spring"
    public List<String> retrieveTodosRelatedToSpring(String user){
        List<String> filterTodos = new ArrayList<>();
        List<String> allTodos = todoService.retrieveTodos(user);

        for (String todo : allTodos){
            if (todo.contains("Spring")){
                filterTodos.add(todo);
            }
        }
        return filterTodos;
    }

    //Borrar de la lista todos aquellos elementos de la lista que no contengan Spring
    public  void deleteTodosNoRelatedToSpring (String user){
        List<String> allTodos = todoService.retrieveTodos(user);

        for (String todo : allTodos){
            if (!todo.contains("Spring")){
                todoService.deleteTodo(todo);
            }
        }
    }
}