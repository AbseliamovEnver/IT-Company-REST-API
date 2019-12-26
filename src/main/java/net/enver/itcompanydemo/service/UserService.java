package net.enver.itcompanydemo.service;

import net.enver.itcompanydemo.model.User;

import java.util.List;

public interface UserService {

    void save(User user);

    void update(Long id, User user);

    User getById(Long id);

    void delete(Long id);

    List<User> getAll();

    User findByUsername(String userName);

    User findByPhoneNumber(String phoneNumber);
}
