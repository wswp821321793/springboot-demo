package com.peng.service.impl;

import com.peng.mapper.UserMapper;
import com.peng.model.User;
import com.peng.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findUserByUsername(String username) {
        return userMapper.findByUserName(username);
    }
}
