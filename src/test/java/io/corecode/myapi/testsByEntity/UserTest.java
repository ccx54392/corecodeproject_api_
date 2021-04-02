package io.corecode.myapi.testsByEntity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import io.corecode.entity.User;
import io.corecode.myapi.abstractClasses.ApiEntityTest;
import org.testng.annotations.BeforeClass;

public class UserTest extends ApiEntityTest {
    @Override
    public String objectToPersist() throws Exception {
        User user = new User();
        user.setUserName("new user");
        user.setPassword("new user");
        user.setRole("user");
        return new ObjectMapper().writeValueAsString(user);
    }

    @Override
    public void setId(String responseBody) throws Exception {
        id=new Gson().fromJson(responseBody, User.class).getUserId();
    }

    @Override
    public String objectToUpdate() throws Exception {
        User user = new User();
        user.setUserName("new user updated");
        user.setPassword("new user updated");
        user.setRole("user");
        return new ObjectMapper().writeValueAsString(user);
    }

    @BeforeClass
    @Override
    public void setApiPath() {
        this.apiPath="/user/";
    }
}
