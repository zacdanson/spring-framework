package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ToDoListView {

    @RequestMapping(value = "/todo-list", method = RequestMethod.GET)
    public String getTodoListView(Model model) {
        return "todoListView";
    }

}


