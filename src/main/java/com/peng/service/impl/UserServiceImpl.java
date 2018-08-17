package com.peng.service.impl;

import com.peng.mapper.UserMapper;
import com.peng.model.User;
import com.peng.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findUserByUsername(String username) {
        return userMapper.findByUserName(username);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    public void register(User newUser) {
        User user = userMapper.findByUserName(newUser.getUsername());
        if(user == null){
            userMapper.insert(newUser);
        }
    }
}
