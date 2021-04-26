package io.corecode.controllers;

import io.corecode.entity.Book;
import io.corecode.entity.Publisher;
import io.corecode.entity.Review;
import io.corecode.entity.Writer;
import io.corecode.service.BookService;
import io.corecode.service.PublisherService;
import io.corecode.service.WriterService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    private BookService bookService;
    private WriterService writerService;
    private PublisherService publisherService;

    @Autowired
    public void setServices(BookService bookService, WriterService writerService, PublisherService publisherService) {
        this.bookService = bookService;
        this.writerService = writerService;
        this.publisherService = publisherService;
    }

    @GetMapping
    public List<Book> list() {
        List<Book> list = bookService.findAll();
        for (Book s : list) {
            s.getWriter().setBooks(null);
            s.getPublisher().setBooks(null);
            List<Review> reviews = s.getReviews();
            for (Review t : reviews) {
                t.setBook(null);
                t.getUser().setReviews(null);
            }
        }
        return list;
    }

    @GetMapping
    @RequestMapping("{id}")
    public Book get(@PathVariable Integer id) {
        Book book = bookService.getOne(id);
        book.getWriter().setBooks(null);
        book.getPublisher().setBooks(null);
        List<Review> reviews = book.getReviews();
        for (Review t : reviews) {
            t.setBook(null);
            t.getUser().setReviews(null);
        }

        return bookService.getOne(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book create(@RequestBody final Book book) {
        Writer writer = writerService.getOne(book.getWriterId());
        writer.setBooks(null);
        book.setWriter(writer);
        Publisher publisher = publisherService.getOne(book.getPublisherId());
        publisher.setBooks(null);
        book.setPublisher(publisher);

        return bookService.saveAndFlush(book);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Integer id) {
        //cascade to remove children
        bookService.deleteById(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public Book update(@PathVariable Integer id, @RequestBody Book book) {
        //validate all values are passed in
        Writer writer = writerService.getOne(book.getWriterId());
        writer.setBooks(null);
        book.setWriter(writer);

        Publisher publisher = publisherService.getOne(book.getPublisherId());
        publisher.setBooks(null);
        book.setPublisher(publisher);

        book.setBookId(id);
        Book existingBook = bookService.getOne(id);
        BeanUtils.copyProperties(book, existingBook, "book_id");

        return bookService.saveAndFlush(existingBook);
    }


}
