package io.corecode.myapi.testsByEntity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import io.corecode.entity.Publisher;
import io.corecode.myapi.abstractClasses.ApiEntityTest;
import org.testng.annotations.BeforeClass;

public class PublisherTest extends ApiEntityTest {
    @Override
    public String objectToPersist() throws Exception {
        Publisher publisher = new Publisher();
        publisher.setName("new publisher");
        return new ObjectMapper().writeValueAsString(publisher);
    }

    @Override
    public void setId(String responseBody) throws Exception {
        id = new Gson().fromJson(responseBody, Publisher.class).getPublisherId();
    }

    @Override
    public String objectToUpdate() throws Exception {
        Publisher publisher = new Publisher();
        publisher.setName("new publisher updated");
        return new ObjectMapper().writeValueAsString(publisher);
    }

    @BeforeClass
    @Override
    public void setApiPath() {
        this.apiPath = "/publisher/";
    }
}
