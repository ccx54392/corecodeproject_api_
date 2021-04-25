package io.corecode.service;

import io.corecode.entity.Publisher;

import java.util.List;

public interface PublisherService {
    public Publisher saveAndFlush(Publisher publisher);
    public void deleteById(int id);
    public Publisher getOne(int id);
    public List<Publisher> findAll();
}
