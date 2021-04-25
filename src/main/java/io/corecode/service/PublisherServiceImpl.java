package io.corecode.service;

import io.corecode.entity.Publisher;
import io.corecode.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherServiceImpl implements PublisherService{

    @Autowired
    private PublisherRepository publisherRepository;

    @Override
    public Publisher saveAndFlush(Publisher publisher) {
        return publisherRepository.saveAndFlush(publisher);
    }

    @Override
    public void deleteById(int id) {
        publisherRepository.deleteById(id);
    }

    @Override
    public Publisher getOne(int id) {
        return publisherRepository.getOne(id);
    }

    @Override
    public List<Publisher> findAll() {
        return (List<Publisher>) publisherRepository.findAll();
    }
}
