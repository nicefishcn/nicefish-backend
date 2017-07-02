package com.nicefish.service;

import com.nicefish.model.User;

import java.util.List;

/**
 * Created by kimmking on 17/6/26.
 */

public interface UserService {

    public User save(User user);

    public User findByUserId(String userId);

    public User findByUserName(String userName);

    public List<User> findAll();
}
