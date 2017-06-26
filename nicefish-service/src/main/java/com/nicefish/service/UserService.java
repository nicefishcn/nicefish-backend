package com.nicefish.service;

import com.nicefish.model.User;

/**
 * Created by kimmking on 17/6/26.
 */

public interface UserService {

    public User save(User user);

    public User findByUserId(String userId);

    public User findByUserNameAndPassword(String userName,String password);
}
