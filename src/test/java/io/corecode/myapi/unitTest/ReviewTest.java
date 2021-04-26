package io.corecode.myapi.unitTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.corecode.controllers.ReviewController;
import io.corecode.entity.*;
import io.corecode.entity.Review;
import io.corecode.service.*;
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
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// mvn test -Dgroups=review

@WebMvcTest(ReviewController.class)
@MockBean(classes = {ReviewService.class, BookService.class, UserService.class})
public class ReviewTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ReviewService service;

    @Autowired
    BookService bookService;
    @Autowired
    UserService userService;

    String path = "/review/";

    @Test(groups = {"review", "unitTest"})
    public void getAll() throws Exception {
        Review entity = (Review) this.getEntity();
        Review entity1 = (Review) this.getEntity();

        Mockito.when(service.findAll()).thenReturn(Arrays.asList(entity, entity1));

        mockMvc.perform(get(path))
                .andExpect(status().isOk())
        ;
        System.out.println("[TEST PASSED] Get all reviews");
    }

    @Test(groups = {"review", "unitTest"})
    public void getById() throws Exception {
        Review entity = (Review) this.getEntity();

        Mockito.when(service.getOne(1)).thenReturn(entity);

        mockMvc.perform(get(path))
                .andExpect(status().isOk())
        ;
        System.out.println("[TEST PASSED] Get review by id");
    }

    @Test(groups = {"review", "unitTest"})
    public void create() throws Exception {
        Review entity = (Review) this.getEntity();

        Mockito.when(bookService.getOne(1)).thenReturn(getBook());
        Mockito.when(userService.getOne(1)).thenReturn(getUser());
        Mockito.when(service.saveAndFlush(entity)).thenReturn(entity);

        mockMvc.perform(MockMvcRequestBuilders
                .post(path)
                .content(new ObjectMapper().writeValueAsString(entity))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
        ;
        System.out.println("[TEST PASSED] Create review");
    }

    @Test(groups = {"review", "unitTest"})
    public void deleteById() throws Exception {
        mockMvc.perform(delete(path + "1"))
                .andExpect(status().isOk())
        ;
        System.out.println("[TEST PASSED] Delete review by id");
    }

    @Test(groups = {"review", "unitTest"})
    public void update() throws Exception {
        Review entity = (Review) this.getEntity();


        Mockito.when(service.getOne(1)).thenReturn(entity);
        Mockito.when(service.saveAndFlush(entity)).thenReturn(entity);

        mockMvc.perform(MockMvcRequestBuilders
                .put(path + "1")
                .content(new ObjectMapper().writeValueAsString(entity))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
        ;
        System.out.println("[TEST PASSED] Update review");
    }

    public Object getEntity() {
        Review entity = new Review();
        entity.setReviewId(1);
        entity.setUserId(1);
        entity.setBookId(1);
        entity.setStars(5);
        entity.setDescription("test qa");
        entity.setBook(getBook());
        entity.setUser(getUser());
        return entity;
    }

    public Book getBook() {
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

    public User getUser() {
        User entity = new User();
        entity.setUserId(1);
        entity.setUserName("user 1");
        entity.setPassword("user 1");
        entity.setRole("user");
        entity.setReviews(new ArrayList<Review>());
        return entity;
    }


}