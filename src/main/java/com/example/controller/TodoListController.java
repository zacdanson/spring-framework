package com.example.controller;

import com.example.service.TodoItemService;
import com.sun.tools.javac.comp.Todo;
import org.springframework.web.bind.annotation.*;
import com.example.domain.TodoItem;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@RestController
public class TodoListController {

    @Autowired
    private TodoItemService todoItemService;

    @RequestMapping(value = "/todo-list", method = RequestMethod.GET)
    public List <TodoItem> getTodoList() {
        return todoItemService.findAllItems();
    }


    @RequestMapping(value = "/todo-list/{id}", method = RequestMethod.GET)
    public TodoItem getTodoById (@PathVariable ("id") Integer id ){
        return this.todoItemService.getTodoById(id);
    }

    @RequestMapping(value = "/user/todo-list/{uid}", method = RequestMethod.GET)
    public List <TodoItem> getTodoByUserId (@PathVariable ("uid") Integer uid){
        return this.todoItemService.getTodoByUid(uid);
    }

    @RequestMapping(value = "/todo-list/{id}", method = RequestMethod.DELETE)
    public void deleteTodoById (@PathVariable ("id") Integer id){
        this.todoItemService.deleteTodoById(id);
    }

    @RequestMapping(value = "/add-todo", method = RequestMethod.POST)
    public void addTodoItem (@RequestBody TodoItem todoItem){
        this.todoItemService.addTodo(todoItem);
    }


    @RequestMapping(value = "/update-todo", method = RequestMethod.POST)
    public void updateTodo (@RequestBody TodoItem todoItem){
        this.todoItemService.updateTodo(todoItem);
    }

}


