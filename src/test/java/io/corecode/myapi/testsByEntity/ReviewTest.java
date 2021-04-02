package io.corecode.myapi.testsByEntity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import io.corecode.entity.Review;
import io.corecode.myapi.abstractClasses.ApiEntityTest;
import org.testng.annotations.BeforeClass;

public class ReviewTest extends ApiEntityTest {
    @Override
    public String objectToPersist() throws Exception {
        Review review = new Review();
        review.setDescription("new review");
        review.setStars(5);
        review.setUserId(1);
        review.setBookId(1);
        return new ObjectMapper().writeValueAsString(review);
    }

    @Override
    public void setId(String responseBody) throws Exception {
        id=new Gson().fromJson(responseBody, Review.class).getReviewId();
    }

    @Override
    public String objectToUpdate() throws Exception {
        Review review = new Review();
        review.setDescription("new review updated");
        review.setStars(4);
        return new ObjectMapper().writeValueAsString(review);
    }

    @BeforeClass
    @Override
    public void setApiPath() {
        this.apiPath="/review/";
    }
}
