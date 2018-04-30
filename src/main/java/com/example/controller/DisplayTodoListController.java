package com.example.controller;

import com.example.domain.TodoItem;
import com.example.service.TodoItemService;
import com.sun.tools.javac.comp.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.validation.Valid;
import java.util.Date;


@Controller
public class DisplayTodoListController {

  @Autowired
  private TodoItemService todoItemService;
  private static final Logger log = LoggerFactory.getLogger(DisplayTodoListController.class);
  private static final String HOMEPAGE_REDIRECT = "redirect:/";
  private static final String TODO_LIST_TEMPLATE = "todoListView";
  private static final String TODO_LIST = "todos";
  private static final String EDITING = "editing";

  private static final String NEW_TODO_ID = "newTodo";

  public String notification;


  @Scheduled(cron = "0 0 0 * * ?")
  public void logTask() {


    for (TodoItem todoItem : todoItemService.findAllItems()) {
        Integer todo_id = todoItem.getId();
        this.todoItemService.updateTimeToComplete(todo_id);
        log.info(todo_id.toString());
    }

  }


  @RequestMapping(value="/", method = RequestMethod.GET)
    public String getTodoList(Model model){
        model.addAttribute(TODO_LIST, this.todoItemService.findAllItems());
        model.addAttribute (NEW_TODO_ID, new TodoItem());

        for(TodoItem todoItem : todoItemService.findAllItems()){
            log.info(todoItem.toString());
        }

        model.addAttribute("notification", notification);
        model.addAttribute(EDITING, false);
        return TODO_LIST_TEMPLATE;
    }

    @RequestMapping(value="/delete/{id}", method = RequestMethod.GET )
    public String deleteTodo (@PathVariable ("id") Integer id){
        this.todoItemService.deleteTodoById(id);
        return HOMEPAGE_REDIRECT;
    }

    @RequestMapping(value="/", method = RequestMethod.POST)
    public String addTodo (Model model,
    @Valid @ModelAttribute (NEW_TODO_ID)
    TodoItem todoItem,
    BindingResult bindingResult
    ){


      Date date = new Date();
      todoItem.setStatus("false");
      todoItem.setTimeToComplete(0);
      todoItem.setDate(date);
      log.info(todoItem.toString());
      String error = bindingResult.getAllErrors().toString();
      log.info(error);
      if(!bindingResult.hasErrors()){

        this.todoItemService.addTodo(todoItem);

        return HOMEPAGE_REDIRECT;

      } else {

          model.addAttribute(TODO_LIST, this.todoItemService.findAllItems());

          return TODO_LIST_TEMPLATE;
      }

    }

    @RequestMapping(value="/update/{id}", method = RequestMethod.GET)
    public String updateTodo(
            Model model,
            @PathVariable ("id") Integer id
    ){
        model.addAttribute(TODO_LIST, this.todoItemService.findAllItems());
        model.addAttribute(NEW_TODO_ID, this.todoItemService.getTodoById(id));
        model.addAttribute(EDITING, true);
        return TODO_LIST_TEMPLATE;
    }

    @RequestMapping("/completed/{id}")
    public String completedTodo(
            Model model,
            @PathVariable ("id") Integer id,
            TodoItem todoItem
    ){

      model.addAttribute(NEW_TODO_ID, this.todoItemService.getTodoById(id));
      TodoItem current = this.todoItemService.getTodoById(id);
      String currentStatus = current.getStatus();

      if(currentStatus.equals("true")){
        current.setStatus("false");
      } else {
        current.setStatus("true");
      }

      this.todoItemService.updateTodo (current);
      return HOMEPAGE_REDIRECT;
    }

    @RequestMapping(value="/update/{id}", method = RequestMethod.POST)
    public String saveTodo(
    Model model,
    @PathVariable ("id") Integer id,
    @Valid @ModelAttribute (NEW_TODO_ID)
    TodoItem newTodo,
    BindingResult bindingResult
    ){
        TodoItem current = this.todoItemService.getTodoById(id);
        current.setName(newTodo.getName());
        current.setDescription(newTodo.getDescription());


        if(!bindingResult.hasErrors()){

            this.todoItemService.updateTodo (current);

            model.addAttribute(EDITING, false);
            return HOMEPAGE_REDIRECT;

        } else {

            model.addAttribute(TODO_LIST, this.todoItemService.findAllItems());
            return TODO_LIST_TEMPLATE;
        }

    }

}
