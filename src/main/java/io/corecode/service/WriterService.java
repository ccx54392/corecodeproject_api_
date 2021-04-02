package io.corecode.service;

import io.corecode.entity.Writer;

import java.util.List;

public interface WriterService {
    public Writer saveAndFlush(Writer writer);
    public void deleteById(int id);
    public Writer getOne(int id);
    public List<Writer> findAll();
}
