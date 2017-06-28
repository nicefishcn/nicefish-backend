package com.nicefish.web.controller;

import com.nicefish.model.User;
import com.nicefish.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by kimmking on 17/6/26.
 */

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    /*@RequestMapping("/login")
    public User login(@RequestParam String userName,@RequestParam String password) {
        return userService.findByUserNameAndPassword(userName,password);
    }*/
    @ApiOperation(value = "login", nickname = "login",response = User.class)
    @ApiResponses({
            @ApiResponse(code = 404, response = String.class, message = "user not found"),
            @ApiResponse(code = 500, response = String.class, message = "Internal server error")
    })
    @RequestMapping(path="/id/{id}",method = RequestMethod.GET)
    public User login(@PathVariable("id") String userId) {
        return userService.findByUserId(userId);
    }

    /*@RequestMapping("/register")
    public User register(@RequestBody User user) {
        user.setUserId(java.util.UUID.randomUUID().toString());
        return userService.save(user);
    }*/
}
