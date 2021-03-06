package io.corecode.myapi.unitTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.corecode.controllers.WriterController;
import io.corecode.entity.Book;
import io.corecode.entity.Writer;
import io.corecode.service.WriterService;
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

// mvn test -Dgroups=writer

@WebMvcTest(WriterController.class)
@MockBean(WriterService.class)
public class WriterTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    WriterService service;

    String path = "/writer/";

    @Test(groups = {"writer", "unitTest"})
    public void getAll() throws Exception {
        Writer entity = (Writer) this.getEntity();
        Writer entity1 = (Writer) this.getEntity();

        Mockito.when(service.findAll()).thenReturn(Arrays.asList(entity, entity1));

        mockMvc.perform(get(path))
                .andExpect(status().isOk())
        ;
        System.out.println("[TEST PASSED] Get all writers");
    }

    @Test(groups = {"writer", "unitTest"})
    public void getById() throws Exception {
        Writer entity = (Writer) this.getEntity();

        Mockito.when(service.getOne(1)).thenReturn(entity);

        mockMvc.perform(get(path))
                .andExpect(status().isOk())
        ;
        System.out.println("[TEST PASSED] Get writer by id");
    }

    @Test(groups = {"writer", "unitTest"})
    public void create() throws Exception {
        Writer entity = (Writer) this.getEntity();

        Mockito.when(service.saveAndFlush(entity)).thenReturn(entity);

        mockMvc.perform(MockMvcRequestBuilders
                .post(path)
                .content(new ObjectMapper().writeValueAsString(entity))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
        ;
        System.out.println("[TEST PASSED] Create writer");
    }

    @Test(groups = {"writer", "unitTest"})
    public void deleteById() throws Exception {
        mockMvc.perform(delete(path
                + "1"))
                .andExpect(status().isOk())
        ;
        System.out.println("[TEST PASSED] Delete writer by id");
    }

    @Test(groups = {"writer", "unitTest"})
    public void update() throws Exception {
        Writer entity = (Writer) this.getEntity();

        Mockito.when(service.getOne(1)).thenReturn(entity);
        Mockito.when(service.saveAndFlush(entity)).thenReturn(entity);

        mockMvc.perform(MockMvcRequestBuilders
                .put(path + "1")
                .content(new ObjectMapper().writeValueAsString(entity))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
        ;
        System.out.println("[TEST PASSED] Update writer");
    }

    public Object getEntity() {
        Writer entity = new Writer();
        entity.setWriterId(1);
        entity.setName("writer 1");
        entity.setBooks(new ArrayList<Book>());
        return entity;
    }


}