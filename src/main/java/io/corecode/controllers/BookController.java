package io.corecode.controllers;

import io.corecode.entity.Book;
import io.corecode.entity.Publisher;
import io.corecode.entity.Review;
import io.corecode.entity.Writer;
import io.corecode.repository.BookRepository;
import io.corecode.repository.PublisherRepository;
import io.corecode.repository.ReviewRepository;
import io.corecode.repository.WriterRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private WriterRepository writerRepository;

    @Autowired
    private PublisherRepository publisherRepository;


    @GetMapping
    public List<Book> list(){
        List<Book> list = bookRepository.findAll();
        for(Book s:list){
            s.getWriter().setBooks(null);
            s.getPublisher().setBooks(null);
            List<Review> reviews = s.getReviews();
            for(Review t:reviews){
                t.setBook(null);
                t.getUser().setReviews(null);
            }
        }
        return list;
    }

    @GetMapping
    @RequestMapping("{id}")
    public Book get(@PathVariable Integer id){
        Book book = bookRepository.getOne(id);
        book.getWriter().setBooks(null);
        book.getPublisher().setBooks(null);
        List<Review> reviews = book.getReviews();
        for(Review t:reviews){
            t.setBook(null);
            t.getUser().setReviews(null);
        }

        return bookRepository.getOne(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book create(@RequestBody final Book book){
        Writer writer = writerRepository.getOne(book.getWriterId());
        writer.setBooks(null);
        book.setWriter(writer);
        Publisher publisher = publisherRepository.getOne(book.getPublisherId());
        publisher.setBooks(null);
        book.setPublisher(publisher);

        return bookRepository.saveAndFlush(book);
    }

    @RequestMapping(value="{id}", method=RequestMethod.DELETE)
    public void delete(@PathVariable Integer id){
        //cascade to remove children
        bookRepository.deleteById(id);
    }

    @RequestMapping(value="{id}", method = RequestMethod.PUT)
    public Book update(@PathVariable Integer id, @RequestBody Book book){
        //validate all values are passed in
        Writer writer = writerRepository.getOne(book.getWriterId());
        writer.setBooks(null);
        book.setWriter(writer);
        Publisher publisher = publisherRepository.getOne(book.getPublisherId());
        publisher.setBooks(null);
        book.setPublisher(publisher);

        book.setBookId(id);
        Book existingBook = bookRepository.getOne(id);
        BeanUtils.copyProperties(book, existingBook,"book_id");
        return bookRepository.saveAndFlush(existingBook);
    }


}
