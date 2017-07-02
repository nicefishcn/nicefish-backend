package com.nicefish.web.controller;

import com.nicefish.model.User;
import com.nicefish.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by kimmking on 17/6/26.
 */

@RestController
@Api("用户管理接口")
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;


    @ApiOperation(value = "find", nickname = "find",response = User.class)
    @ApiResponses({
            @ApiResponse(code = 404, response = String.class, message = "user not found"),
            @ApiResponse(code = 500, response = String.class, message = "Internal server error")
    })
    @RequestMapping(path="/find/{id}",method = RequestMethod.GET)
    public User find(@PathVariable("id") String userId) {
        return userService.findByUserId(userId);
    }


    @ApiOperation(value = "list", nickname = "list",response = User.class)
    @ApiResponses({
            @ApiResponse(code = 404, response = String.class, message = "user not found"),
            @ApiResponse(code = 500, response = String.class, message = "Internal server error")
    })
    @RequestMapping(path="/list",method = RequestMethod.GET)
    public List<User> list(){
        return userService.findAll();
    }

}
