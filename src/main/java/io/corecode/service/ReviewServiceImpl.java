package io.corecode.service;

import io.corecode.entity.Review;
import io.corecode.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public Review saveAndFlush(Review review) {
        return reviewRepository.saveAndFlush(review);
    }

    @Override
    public void deleteById(int id) {
        reviewRepository.deleteById(id);
    }

    @Override
    public Review getOne(int id) {
        return reviewRepository.getOne(id);
    }

    @Override
    public List<Review> findAll() {
        return (List<Review>) reviewRepository.findAll();
    }
}
