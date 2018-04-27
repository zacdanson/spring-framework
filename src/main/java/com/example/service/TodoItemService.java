package com.example.service;

import com.example.domain.TodoItem;
import com.example.domain.TodoItemRepository;
import com.sun.tools.javac.comp.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class TodoItemService {

    @Autowired
    private TodoItemRepository todoItemRepository;

    public List <TodoItem> findAllItems () {
            return this.todoItemRepository.findAll ();
    }

    public TodoItem getTodoById (Integer id) {
        return this.todoItemRepository.findTodoById(id);
    }

    public List <TodoItem> getTodoByUid (Integer uid){
        return this.todoItemRepository.getTodoByUid(uid);
    }

    public void deleteTodoById (Integer id){
        this.todoItemRepository.delete(id);
    }

    public void addTodo (TodoItem todoItem){
        this.todoItemRepository.save(todoItem);
    }

    public void updateTodo (TodoItem todoItem){
        this.todoItemRepository.save(todoItem);
    }

}
