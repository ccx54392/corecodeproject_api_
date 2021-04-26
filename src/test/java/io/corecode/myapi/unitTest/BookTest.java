package io.corecode.myapi.unitTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.corecode.controllers.BookController;
import io.corecode.entity.*;
import io.corecode.entity.Book;
import io.corecode.service.BookService;
import io.corecode.service.PublisherService;
import io.corecode.service.WriterService;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// mvn test -Dgroups=book

@WebMvcTest(BookController.class)
@MockBean(classes = {BookService.class, WriterService.class, PublisherService.class})
public class BookTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    BookService service;

    @Autowired
    WriterService writerService;

    @Autowired
    PublisherService publisherService;

    String path = "/book/";

    @Test(groups = {"book", "unitTest"})
    public void getAll() throws Exception {
        Book entity = (Book) this.getEntity();
        Book entity1 = (Book) this.getEntity();

        Mockito.when(service.findAll()).thenReturn(Arrays.asList(entity, entity1));

        mockMvc.perform(get(path))
                .andExpect(status().isOk())
        ;
        System.out.println("[TEST PASSED] Get all books");
    }

    @Test(groups = {"book", "unitTest"})
    public void getById() throws Exception {
        Book entity = (Book) this.getEntity();

        Mockito.when(service.getOne(1)).thenReturn(entity);

        mockMvc.perform(get(path))
                .andExpect(status().isOk())
        ;
        System.out.println("[TEST PASSED] Get book by id");
    }

    @Test(groups = {"book", "unitTest"})
    public void create() throws Exception {
        Book entity = (Book) this.getEntity();

        Mockito.when(writerService.getOne(1)).thenReturn(getWriter());
        Mockito.when(publisherService.getOne(1)).thenReturn(getPublisher());
        Mockito.when(service.saveAndFlush(entity)).thenReturn(entity);

        mockMvc.perform(MockMvcRequestBuilders
                .post(path)
                .content(new ObjectMapper().writeValueAsString(entity))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
        ;
        System.out.println("[TEST PASSED] Create book");
    }

    @Test(groups = {"book", "unitTest"})
    public void deleteById() throws Exception {
        mockMvc.perform(delete(path + "1"))
                .andExpect(status().isOk())
        ;
        System.out.println("[TEST PASSED] Delete book by id");
    }

    @Test(groups = {"book", "unitTest"})
    public void update() throws Exception {
        Book entity = (Book) this.getEntity();

        Mockito.when(writerService.getOne(1)).thenReturn(getWriter());
        Mockito.when(publisherService.getOne(1)).thenReturn(getPublisher());
        Mockito.when(service.getOne(1)).thenReturn(entity);
        Mockito.when(service.saveAndFlush(entity)).thenReturn(entity);

        mockMvc.perform(MockMvcRequestBuilders
                .put(path + "1")
                .content(new ObjectMapper().writeValueAsString(entity))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
        ;
        System.out.println("[TEST PASSED] Update book");
    }

    public Object getEntity() {
        Book entity = new Book();
        entity.setBookId(1);
        entity.setTitle("book 1");
        entity.setCover("");
        entity.setPicture("");
        entity.setWriterId(1);
        entity.setPublisherId(1);
        entity.setDescription("book 1");
        // publisher
        entity.setPublisher(getPublisher());
        // writer
        entity.setWriter(getWriter());
        entity.setReviews(new ArrayList<Review>());
        return entity;
    }

    public Writer getWriter() {
        Writer writer = new Writer();
        writer.setWriterId(1);
        writer.setBooks(new ArrayList<Book>());
        writer.setName("writer 1");
        return writer;
    }

    public Publisher getPublisher() {
        Publisher publisher = new Publisher();
        publisher.setBooks(new ArrayList<Book>());
        publisher.setPublisherId(1);
        publisher.setName("Publisher 1");
        return publisher;
    }


}