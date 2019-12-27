package net.enver.itcompanydemo.service;

import net.enver.itcompanydemo.model.User;

public interface UserService extends BaseService<User> {

    User findByUsername(String username);

    User findByPhoneNumber(String phoneNumber);
}
