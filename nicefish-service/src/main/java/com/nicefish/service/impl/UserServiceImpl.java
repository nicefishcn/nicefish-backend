package com.nicefish.service.impl;

import com.nicefish.mapper.UserMapper;
import com.nicefish.model.User;
import com.nicefish.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by kimmking on 17/6/26.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User save(User user) {
        userMapper.insert(user);
        return user;
    }

    @Override
    public User findByUserId(String userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public User findByUserNameAndPassword(String userName, String password) {
        return userMapper.findByUserNameAndPassword(userName,password);
    }
}
