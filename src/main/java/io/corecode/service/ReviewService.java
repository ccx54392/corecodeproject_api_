package io.corecode.service;

import io.corecode.entity.Review;

import java.util.List;

public interface ReviewService {
    public Review saveAndFlush(Review review);
    public void deleteById(int id);
    public Review getOne(int id);
    public List<Review> findAll();
}
