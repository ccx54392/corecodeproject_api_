package io.corecode.myapi.unitTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.corecode.controllers.PublisherController;
import io.corecode.entity.Book;
import io.corecode.entity.Publisher;
import io.corecode.service.PublisherService;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// mvn test -Dgroups=publisher

@WebMvcTest(PublisherController.class)
@MockBean(PublisherService.class)
public class PublisherTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    PublisherService service;

    String path = "/publisher/";

    @Test(groups = {"publisher", "unitTest"})
    public void getAll() throws Exception {
        Publisher entity = (Publisher) this.getEntity();
        Publisher entity1 = (Publisher) this.getEntity();

        Mockito.when(service.findAll()).thenReturn(Arrays.asList(entity, entity1));

        mockMvc.perform(get(path))
                .andExpect(status().isOk())
        ;
        System.out.println("[TEST PASSED] Get all publishers");
    }

    @Test(groups = {"publisher", "unitTest"})
    public void getById() throws Exception {
        Publisher entity = (Publisher) this.getEntity();

        Mockito.when(service.getOne(1)).thenReturn(entity);

        mockMvc.perform(get(path))
                .andExpect(status().isOk())
        ;
        System.out.println("[TEST PASSED] Get publisher by id");
    }

    @Test(groups = {"publisher", "unitTest"})
    public void create() throws Exception {
        Publisher entity = (Publisher) this.getEntity();

        Mockito.when(service.saveAndFlush(entity)).thenReturn(entity);

        mockMvc.perform(MockMvcRequestBuilders
                .post(path)
                .content(new ObjectMapper().writeValueAsString(entity))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
        ;
        System.out.println("[TEST PASSED] Create publisher");
    }

    @Test(groups = {"publisher", "unitTest"})
    public void deleteById() throws Exception {
        mockMvc.perform(delete(path + "1"))
                .andExpect(status().isOk())
        ;
        System.out.println("[TEST PASSED] Delete publisher by id");
    }

    @Test(groups = {"publisher", "unitTest"})
    public void update() throws Exception {
        Publisher entity = (Publisher) this.getEntity();

        Mockito.when(service.getOne(1)).thenReturn(entity);
        Mockito.when(service.saveAndFlush(entity)).thenReturn(entity);

        mockMvc.perform(MockMvcRequestBuilders
                .put(path + "1")
                .content(new ObjectMapper().writeValueAsString(entity))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
        ;
        System.out.println("[TEST PASSED] Update publisher");
    }

    public Object getEntity() {
        Publisher entity = new Publisher();
        entity.setPublisherId(1);
        entity.setName("publisher 1");
        entity.setBooks(new ArrayList<Book>());
        return entity;
    }


}