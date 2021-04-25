package io.corecode.service;

import io.corecode.entity.Book;

import java.util.List;

public interface BookService {
    public Book saveAndFlush(Book book);
    public void deleteById(int id);
    public Book getOne(int id);
    public List<Book> findAll();
}
