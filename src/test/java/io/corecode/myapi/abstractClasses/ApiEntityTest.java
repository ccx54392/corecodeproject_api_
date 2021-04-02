package io.corecode.myapi.abstractClasses;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import io.corecode.MyApiApplication;
import io.corecode.entity.Writer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.BeforeClass;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = MyApiApplication.class)
public abstract class ApiEntityTest extends AbstractTestNGSpringContextTests {

    @Autowired
    WebApplicationContext webApplicationContext;
    MockMvc mvc;
    protected int id;
    protected String apiPath = ""; //**

    public abstract String objectToPersist() throws Exception;

    public abstract void setId(String responseBody) throws Exception;

    public abstract String objectToUpdate() throws Exception;

    @BeforeClass
    public abstract void setApiPath();

    @BeforeClass
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
    }

    @org.testng.annotations.Test(description = "get all registries")
    public void getAll() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get(this.apiPath))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @org.testng.annotations.Test(priority = 0,description = "create a registry")
    public void create() throws Exception {
        ResultActions result = mvc.perform(MockMvcRequestBuilders
                .post(this.apiPath)
                .content(this.objectToPersist())
                .contentType(MediaType.APPLICATION_JSON));
        result.andExpect(status().isCreated()).andDo(print());

        String responseBody = result.andReturn().getResponse().getContentAsString();
        this.setId(responseBody);
    }


    @org.testng.annotations.Test(priority = 1,description = "get one registry")
    public void getOne() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get(this.apiPath + "{id}", this.id)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @org.testng.annotations.Test(priority = 2, description = "update a registry")
    public void update() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .put(this.apiPath + "{id}", this.id)
                .content(this.objectToUpdate())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());

    }

    @org.testng.annotations.Test(priority = 3, description = "delete a registry")
    public void delete() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete(this.apiPath + "{id}", this.id))
                .andExpect(status().isOk())
                .andDo(print());
    }

}