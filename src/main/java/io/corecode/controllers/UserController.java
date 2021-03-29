package io.corecode.controllers;

import io.corecode.entity.Book;
import io.corecode.entity.Review;
import io.corecode.entity.User;
import io.corecode.repository.ReviewRepository;
import io.corecode.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<User> list(){
        List<User> users = userRepository.findAll();
        for(User s: users){
            for(Review t: s.getReviews()){
                t.setUser(null);
                t.getBook().getPublisher().setBooks(null);
                t.getBook().getWriter().setBooks(null);
                t.getBook().setReviews(null);
            }
        }
        return users;
    }

    @GetMapping
    @RequestMapping("{id}")
    public User get(@PathVariable Integer id){
        User user = userRepository.getOne(id);
        for(Review t: user.getReviews()){
            t.setUser(null);
            t.getBook().getPublisher().setBooks(null);
            t.getBook().getWriter().setBooks(null);
            t.getBook().setReviews(null);
        }
        return user;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@RequestBody final User session){
        return userRepository.saveAndFlush(session);
    }

    @RequestMapping(value="{id}", method=RequestMethod.DELETE)
    public void delete(@PathVariable Integer id){
        //cascade to remove children
        userRepository.deleteById(id);
    }

    @RequestMapping(value="{id}", method = RequestMethod.PUT)
    public User update(@PathVariable Integer id, @RequestBody User user){
        //validate all values are passed in
        user.setUserId(id);
        User existingUser = userRepository.getOne(id);
        BeanUtils.copyProperties(user, existingUser,"user_id");
        return userRepository.saveAndFlush(existingUser);
    }


}
