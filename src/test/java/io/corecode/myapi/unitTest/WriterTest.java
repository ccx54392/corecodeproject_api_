package io.corecode.myapi.unitTest;

import io.corecode.controllers.WriterController;
import io.corecode.entity.Book;
import io.corecode.entity.Writer;
import io.corecode.service.WriterService;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.Arrays;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(WriterController.class)
@MockBean(WriterService.class)
public class WriterTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    WriterService writerService;

    @Test
    public void getAllWriters() throws Exception {
        Writer writer = new Writer();
        writer.setWriterId(1);
        writer.setName("writer 1");
        writer.setBooks(new ArrayList<Book>());

        Writer writer2 = new Writer();
        writer2.setWriterId(2);
        writer2.setName("writer 2");
        writer2.setBooks(new ArrayList<Book>());

        Mockito.when(writerService.findAll()).thenReturn(Arrays.asList(writer, writer2));

        mockMvc.perform(get("/writer/"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void getWriterById() throws Exception {
        Writer writer = new Writer();
        writer.setWriterId(1);
        writer.setName("writer 1");
        writer.setBooks(new ArrayList<Book>());

        Mockito.when(writerService.getOne(1)).thenReturn(writer);

        mockMvc.perform(get("/writer/1"))
                .andExpect(status().isOk())
                .andDo(print());
    }
}