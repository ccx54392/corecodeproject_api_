package io.corecode.controllers;


import io.corecode.entity.Book;
import io.corecode.entity.Publisher;
import io.corecode.entity.Review;
import io.corecode.service.PublisherService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/publisher")
public class PublisherController {

    @Autowired
    private PublisherService publisherService;

    @GetMapping
    public List<Publisher> list() {
        List<Publisher> list = publisherService.findAll();
        for (Publisher s : list) {
            List<Book> books = s.getBooks();
            for (Book t : books) {
                t.setPublisher(null);
                t.getWriter().setBooks(null);
                List<Review> reviews = t.getReviews();
                for (Review u : reviews) {
                    u.setBook(null);
                    u.getUser().setReviews(null);
                }
            }
        }
        return list;
    }

    @GetMapping
    @RequestMapping("{id}")
    public Publisher get(@PathVariable Integer id) {
        Publisher publisher = publisherService.getOne(id);
        for (Book s : publisher.getBooks()) {
            s.setPublisher(null);
            s.getWriter().setBooks(null);
            List<Review> reviews = s.getReviews();
            for (Review u : reviews) {
                u.setBook(null);
                u.getUser().setReviews(null);
            }
        }

        return publisherService.getOne(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Publisher create(@RequestBody final Publisher session) {
        return publisherService.saveAndFlush(session);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Integer id) {
        //cascade to remove children
        publisherService.deleteById(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public Publisher update(@PathVariable Integer id, @RequestBody Publisher publisher) {
        //validate all values are passed in
        publisher.setPublisherId(id);
        Publisher existingPublisher = publisherService.getOne(id);
        BeanUtils.copyProperties(publisher, existingPublisher, "publisher_id");
        return publisherService.saveAndFlush(existingPublisher);
    }


}
