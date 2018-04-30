package com.example.service;

import com.example.domain.TodoItem;
import com.example.domain.TodoItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    public List <TodoItem> getTodoByName (String name){
        return this.todoItemRepository.getTodoByName(name);
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

    public void updateTimeToComplete(Integer id){

      TodoItem todo = this.getTodoById(id);
      if(todo.getStatus().equals("false")){
        Integer time = todo.getTimeToComplete();
        Integer newTime = time+1;
        todo.setTimeToComplete(newTime);
        this.todoItemRepository.save(todo);

      }
    }



}
