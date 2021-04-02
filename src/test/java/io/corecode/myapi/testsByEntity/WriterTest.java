package io.corecode.myapi.testsByEntity;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import io.corecode.MyApiApplication;
import io.corecode.entity.Writer;
import io.corecode.myapi.dataProviders.WriterDataProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = MyApiApplication.class)
public class WriterTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mvc;
    private int id;
    private String apiPath = "/writer/"; //**

    public String objectToPersist() throws Exception{
        Writer writer = new Writer(); //**
        writer.setName("new writer"); //**
        return new ObjectMapper().writeValueAsString(writer);//**
    }

    public void setId(String responseBody) throws Exception{
        id=new Gson().fromJson(responseBody, Writer.class).getWriterId(); //**
    }

    public String objectToUpdate() throws Exception{
        Writer writer = new Writer();
        writer.setName("new writer updated");
        return new ObjectMapper().writeValueAsString(writer);

    }

    @BeforeClass
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
    }

    @Test(description = "get all registries")
    public void getAll() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get(this.apiPath))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test(priority = 0,description = "create a registry",dataProvider = "persistWriters",dataProviderClass = WriterDataProvider.class)
    public void create(String jsonObject, int status) throws Exception {
        ResultActions result = mvc.perform(MockMvcRequestBuilders
                .post(this.apiPath)
                .content(jsonObject)
                .contentType(MediaType.APPLICATION_JSON));
        result.andDo(print());

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(result.andReturn().getResponse().getStatus(),status,"The method was not executed successfully");
        softAssert.assertAll();

        String responseBody = result.andReturn().getResponse().getContentAsString();
        this.setId(responseBody);
    }


    @Test(priority = 1,description = "get one registry")
    public void getOne() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get(this.apiPath + "{id}", this.id)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test(priority = 2, description = "update a registry")
    public void update() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .put(this.apiPath + "{id}", this.id)
                .content(this.objectToUpdate())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());

    }

    @Test(priority = 3, description = "delete a registry")
    public void delete() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete(this.apiPath + "{id}", this.id))
                .andExpect(status().isOk())
                .andDo(print());
    }

}