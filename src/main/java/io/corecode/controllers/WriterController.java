package io.corecode.controllers;


import io.corecode.entity.Book;
import io.corecode.entity.Review;
import io.corecode.entity.Writer;
import io.corecode.service.WriterService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/writer")
public class WriterController {
    private WriterService writerService;

    @Autowired
    public void setWriterService(WriterService writerService) {
        this.writerService=writerService;
    }

    @GetMapping
    public List<Writer> list(){
        List<Writer> list = writerService.findAll();
        for(Writer s:list){
            List<Book> books = s.getBooks();
            for(Book t:books){
                t.setWriter(null);
                t.getPublisher().setBooks(null);
                List<Review> reviews=t.getReviews();
                for(Review u:reviews){
                    u.setBook(null);
                    u.getUser().setReviews(null);
                }
            }
        }
        return list;
    }

    @GetMapping
    @RequestMapping("{id}")
    public Writer get(@PathVariable Integer id){
        Writer writer = writerService.getOne(id);
        for(Book s:writer.getBooks()){
            s.setWriter(null);
            s.getPublisher().setBooks(null);
            List<Review> reviews=s.getReviews();
            for(Review u:reviews){
                u.setBook(null);
                u.getUser().setReviews(null);
            }
        }
        return writer;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Writer create(@RequestBody final Writer writer){

        return writerService.saveAndFlush(writer);
    }

    @RequestMapping(value="{id}", method=RequestMethod.DELETE)
    public void delete(@PathVariable Integer id){
        //cascade to remove children
        writerService.deleteById(id);
    }

    @RequestMapping(value="{id}", method = RequestMethod.PUT)
    public Writer update(@PathVariable Integer id, @RequestBody Writer writer){
        //validate all values are passed in
        writer.setWriterId(id);
        Writer existingWriter = writerService.getOne(id);
        BeanUtils.copyProperties(writer, existingWriter,"writer_id");
        return writerService.saveAndFlush(existingWriter);
    }


}
