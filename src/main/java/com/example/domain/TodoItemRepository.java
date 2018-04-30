package com.example.domain;

import com.sun.tools.javac.comp.Todo;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;


public interface TodoItemRepository
    extends CrudRepository <TodoItem, Integer> {

    @Override

    List <TodoItem> findAll ();

    TodoItem findTodoById (Integer id);

    List <TodoItem> getTodoByName (String name);

    
}
