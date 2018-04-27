package com.example.domain;

import org.springframework.data.repository.CrudRepository;
import java.util.List;


public interface TodoItemRepository
    extends CrudRepository <TodoItem, Integer> {

    @Override

    List <TodoItem> findAll ();

    TodoItem findTodoById (Integer id);

    List <TodoItem> getTodoByUid (Integer uid);

}
