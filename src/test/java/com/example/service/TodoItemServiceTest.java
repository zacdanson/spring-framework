package com.example.service;

import com.example.DemoApplicationTests;
import com.example.controller.DisplayTodoListController;
import com.example.domain.TodoItem;
import com.example.service.TodoItemService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.List;
import java.util.logging.Logger;

@Transactional
public class TodoItemServiceTest extends DemoApplicationTests {


    @Autowired
    private TodoItemService todoItemService;
    private org.slf4j.Logger log = LoggerFactory.getLogger(this.getClass());


    @Test
    public void testFindAll(){

        List <TodoItem> list = todoItemService.findAllItems();

        Assert.assertNotNull("Test Failed expected result to not be null.", list);
        Assert.assertEquals("Test Failed expected result size to equal 3 ", 3, list.size() );

        log.info(list.toString());
    }

    @Test
    public void updateTimeToComplete(){

        List <TodoItem> list = todoItemService.findAllItems();

        for (TodoItem todo : list){
            Integer currentCount = todo.getTimeToComplete();
            todoItemService.updateTimeToComplete(todo.getId());
            Integer newCount = todo.getTimeToComplete();
            if(todo.getStatus().equals("true")){
                Assert.assertEquals("Test Failed expected result to be the same as current count.", currentCount, newCount);
            } else {
                currentCount+=1;
                Assert.assertEquals("Test Failed expected result to be incremented by 1. ", currentCount, newCount);
            }
        }

    }

}
