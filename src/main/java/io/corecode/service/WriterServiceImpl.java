package io.corecode.service;

import io.corecode.entity.Writer;
import io.corecode.repository.WriterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WriterServiceImpl implements WriterService{
    @Autowired
    private WriterRepository writerRepository;

    @Override
    public Writer saveAndFlush(Writer writer) {
        return writerRepository.saveAndFlush(writer);
    }

    @Override
    public void deleteById(int id) {
        writerRepository.deleteById(id);
    }

    @Override
    public Writer getOne(int id) {
        return writerRepository.getOne(id);
    }

    @Override
    public List<Writer> findAll() {
        return (List<Writer>) writerRepository.findAll();
    }
}
