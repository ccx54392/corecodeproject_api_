package io.corecode.myapi.testsByEntity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import io.corecode.entity.Book;
import io.corecode.myapi.abstractClasses.ApiEntityTest;
import org.testng.annotations.BeforeClass;

public class BookTest extends ApiEntityTest {
    @Override
    public String objectToPersist() throws Exception {
        Book book = new Book();
        book.setTitle("new book");
        book.setWriterId(1);
        book.setPublisherId(1);
        book.setDescription("new book");
        return new ObjectMapper().writeValueAsString(book);
    }

    @Override
    public void setId(String responseBody) throws Exception {
        id=new Gson().fromJson(responseBody, Book.class).getBookId();
    }

    @Override
    public String objectToUpdate() throws Exception {
        Book book = new Book();
        book.setTitle("new book updated");
        book.setWriterId(2);
        book.setPublisherId(2);
        book.setDescription("new book updated");
        return new ObjectMapper().writeValueAsString(book);
    }

    @BeforeClass
    @Override
    public void setApiPath() {
        this.apiPath="/book/";
    }
}
