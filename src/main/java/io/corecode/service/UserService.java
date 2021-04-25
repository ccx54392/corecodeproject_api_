package io.corecode.service;

import io.corecode.entity.User;

import java.util.List;

public interface UserService {
    public User saveAndFlush(User user);
    public void deleteById(int id);
    public User getOne(int id);
    public List<User> findAll();
}
