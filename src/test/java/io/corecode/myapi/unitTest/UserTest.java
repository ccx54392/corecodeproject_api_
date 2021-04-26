package io.corecode.myapi.unitTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.corecode.controllers.UserController;
import io.corecode.entity.Review;
import io.corecode.entity.User;
import io.corecode.service.UserService;
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

// mvn test -Dgroups=user

@WebMvcTest(UserController.class)
@MockBean(UserService.class)
public class UserTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    UserService service;

    String path = "/user/";

    @Test(groups = {"user", "unitTest"})
    public void getAll() throws Exception {
        User entity = (User) this.getEntity();
        User entity1 = (User) this.getEntity();

        Mockito.when(service.findAll()).thenReturn(Arrays.asList(entity, entity1));

        mockMvc.perform(get(path))
                .andExpect(status().isOk())
        ;
        System.out.println("[TEST PASSED] Get all users");
    }

    @Test(groups = {"user", "unitTest"})
    public void getById() throws Exception {
        User entity = (User) this.getEntity();

        Mockito.when(service.getOne(1)).thenReturn(entity);

        mockMvc.perform(get(path))
                .andExpect(status().isOk())
        ;
        System.out.println("[TEST PASSED] Get user by id");
    }

    @Test(groups = {"user", "unitTest"})
    public void create() throws Exception {
        User entity = (User) this.getEntity();

        Mockito.when(service.saveAndFlush(entity)).thenReturn(entity);

        mockMvc.perform(MockMvcRequestBuilders
                .post(path)
                .content(new ObjectMapper().writeValueAsString(entity))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
        ;
        System.out.println("[TEST PASSED] Create user");
    }

    @Test(groups = {"user", "unitTest"})
    public void deleteById() throws Exception {
        mockMvc.perform(delete(path + "1"))
                .andExpect(status().isOk())
        ;
        System.out.println("[TEST PASSED] Delete user by id");
    }

    @Test(groups = {"user", "unitTest"})
    public void update() throws Exception {
        User entity = (User) this.getEntity();

        Mockito.when(service.getOne(1)).thenReturn(entity);
        Mockito.when(service.saveAndFlush(entity)).thenReturn(entity);

        mockMvc.perform(MockMvcRequestBuilders
                .put(path + "1")
                .content(new ObjectMapper().writeValueAsString(entity))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
        ;
        System.out.println("[TEST PASSED] Update user");
    }

    public Object getEntity() {
        User entity = new User();
        entity.setUserId(1);
        entity.setUserName("user 1");
        entity.setPassword("user 1");
        entity.setRole("user");
        entity.setReviews(new ArrayList<Review>());
        return entity;
    }


}