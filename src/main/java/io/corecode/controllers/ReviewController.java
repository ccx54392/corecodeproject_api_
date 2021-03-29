package io.corecode.controllers;

import io.corecode.entity.Book;
import io.corecode.entity.Review;
import io.corecode.repository.BookRepository;
import io.corecode.repository.ReviewRepository;
import io.corecode.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<Review> list(){
        List<Review> reviews = reviewRepository.findAll();
        for(Review s: reviews){
            s.getBook().setReviews(null);
            s.getBook().getPublisher().setBooks(null);
            s.getBook().getWriter().setBooks(null);
            s.getUser().setReviews(null);
        }
        return reviews;
    }

    @GetMapping
    @RequestMapping("{id}")
    public Review get(@PathVariable Integer id){
        Review review = reviewRepository.getOne(id);
        review.getBook().setReviews(null);
        review.getBook().getPublisher().setBooks(null);
        review.getBook().getWriter().setBooks(null);
        review.getUser().setReviews(null);
        return review;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Review create(@RequestBody final Review review){
        review.setBook(bookRepository.getOne(review.getBookId()));
        review.getBook().setReviews(null);
        review.getBook().getPublisher().setBooks(null);
        review.getBook().getWriter().setBooks(null);
        review.setUser(userRepository.getOne(review.getUserId()));
        review.getUser().setReviews(null);
        return reviewRepository.saveAndFlush(review);
    }

    @RequestMapping(value="{id}", method=RequestMethod.DELETE)
    public void delete(@PathVariable Integer id){
        //cascade to remove children
        reviewRepository.deleteById(id);
    }

    @RequestMapping(value="{id}", method = RequestMethod.PUT)
    public Review update(@PathVariable Integer id, @RequestBody Review review){
        review.setReviewId(id);
        Review existingReview = reviewRepository.getOne(id);
        review.setUser(existingReview.getUser());
        review.setBook(existingReview.getBook());

        review.getBook().setReviews(null);
        review.getBook().getPublisher().setBooks(null);
        review.getBook().getWriter().setBooks(null);
        review.getUser().setReviews(null);

        BeanUtils.copyProperties(review, existingReview,"review_id");

        return reviewRepository.saveAndFlush(existingReview);
    }


}
