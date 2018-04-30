package com.example.web.api;
import com.example.DemoApplicationControllerTests;
import com.example.domain.TodoItem;
import com.example.service.TodoItemService;
import io.restassured.RestAssured;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import sun.rmi.runtime.Log;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.logging.Logger;

@Transactional
public class TodoListControllerTest extends DemoApplicationControllerTests {

    @Autowired
    private TodoItemService todoItemService;
    private Matchers matchers;
    private org.slf4j.Logger log = LoggerFactory.getLogger(this.getClass());


    @Before
    public void setUp(){
        super.setup();
        RestAssured.port = 8082;
    }

    @Test
    public void testGetTodoItem() throws Exception {
        String uri = "/api/todo-list";
        RestAssured.get(uri).then().body("id", Matchers.hasItems(11,22,33));
    }

    @Test
    public void testGetItemById() throws Exception {
        String uri = "/api/todo-list/{id}";
        Integer id = 22;

        RestAssured.get(uri, id)
                .then()
                .statusCode(200)
                .body("id", Matchers.is(22));
    }

    @Test
    public void failedGetItemById() throws Exception {
        String uri = "/api/todo-list/{id}";
        Integer id = 100;

        RestAssured.get(uri, id).then().body(Matchers.equalTo(""));
    }

    @Test
    public void updateItem() throws Exception {
        String uri = "/api/update-todo";
        Integer id = 11;
        TodoItem todoItem = todoItemService.getTodoById(id);
        todoItem.setStatus("true");

        RestAssured
          .given()
          .contentType("application/json")
          .body(todoItem)
          .post(uri)
          .then()
          .statusCode(200);

        RestAssured
          .get("/api/todo-list/{id}", id)
          .then()
          .body("status", Matchers.equalTo("true"));

    }

    @Test
    public void deleteItem() throws Exception {
      String uri = "/api/todo-list/{id}";
      Integer id = 11;

      RestAssured
        .delete(uri, id)
        .then()
        .statusCode(200);

      RestAssured
        .get(uri, id)
        .then()
        .body(Matchers.equalTo(""));

    }

    @Test
    public void addItem() throws Exception {

      String uri = "/api/add-todo";
      TodoItem todoItem = new TodoItem();
      todoItem.setStatus("false");
      todoItem.setDescription("test todo item.");
      todoItem.setName("test");
      todoItem.setDate(new Date());
      todoItem.setTimeToComplete(0);

      log.info(todoItem.toString());

      RestAssured
        .given()
        .contentType("application/json")
        .body(todoItem)
        .post(uri)
        .then()
        .log()
        .all()
        .statusCode(200);

      RestAssured
        .get("/api/todo-list")
        .then()
        .log()
        .all()
        .statusCode(200)
        .body("size()", Matchers.equalTo(4))
        .body("description", Matchers.hasItem("test todo item."));

    }


}
