package com.example;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import java.io.IOException;

import static io.restassured.RestAssured.basic;

@WebAppConfiguration
public abstract class DemoApplicationControllerTests extends DemoApplicationTests {

    protected MockMvc mvc;

    @Autowired
    protected WebApplicationContext webApplicationContext;


    protected void setup(){
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        RestAssured.port = 8082;
    }

    protected String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(obj);
    }

    protected <T> T mapFromJson(String json, Class<T> clazz) throws JsonParseException, JsonMappingException, IOException {

        ObjectMapper mapper = new ObjectMapper();

        return mapper.readValue(json, clazz);
    }


}
