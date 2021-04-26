package io.corecode.controllers;

import io.corecode.entity.Review;
import io.corecode.service.BookService;
import io.corecode.service.ReviewService;
import io.corecode.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;
    @Autowired
    private BookService bookService;
    @Autowired
    private UserService userService;

    @GetMapping
    public List<Review> list() {
        List<Review> reviews = reviewService.findAll();
        for (Review s : reviews) {
            s.getBook().setReviews(null);
            s.getBook().getPublisher().setBooks(null);
            s.getBook().getWriter().setBooks(null);
            s.getUser().setReviews(null);
        }
        return reviews;
    }

    @GetMapping
    @RequestMapping("{id}")
    public Review get(@PathVariable Integer id) {
        Review review = reviewService.getOne(id);
        review.getBook().setReviews(null);
        review.getBook().getPublisher().setBooks(null);
        review.getBook().getWriter().setBooks(null);
        review.getUser().setReviews(null);
        return review;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Review create(@RequestBody final Review review) {
        review.setBook(bookService.getOne(review.getBookId()));
        review.getBook().setReviews(null);
        review.getBook().getPublisher().setBooks(null);
        review.getBook().getWriter().setBooks(null);
        review.setUser(userService.getOne(review.getUserId()));
        review.getUser().setReviews(null);
        return reviewService.saveAndFlush(review);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Integer id) {
        //cascade to remove children
        reviewService.deleteById(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public Review update(@PathVariable Integer id, @RequestBody Review review) {
        review.setReviewId(id);
        Review existingReview = reviewService.getOne(id);
        review.setUser(existingReview.getUser());
        review.setBook(existingReview.getBook());

        review.getBook().setReviews(null);
        review.getBook().getPublisher().setBooks(null);
        review.getBook().getWriter().setBooks(null);
        review.getUser().setReviews(null);

        BeanUtils.copyProperties(review, existingReview, "review_id");

        return reviewService.saveAndFlush(existingReview);
    }


}
