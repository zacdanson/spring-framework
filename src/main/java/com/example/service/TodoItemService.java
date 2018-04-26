package com.example.service;

import com.example.domain.TodoItem;
import com.example.domain.TodoItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class TodoItemService {

    @Autowired
    private TodoItemRepository todoItemRepository;

    public List <TodoItem> findAllEntries () {
            return this.todoItemRepository.findAll ();
    }


}
