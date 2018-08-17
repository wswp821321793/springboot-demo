package com.peng.service;

import com.peng.model.User;

public interface UserService {
    User findUserByUsername(String username);

    void register(User newUser);
}
