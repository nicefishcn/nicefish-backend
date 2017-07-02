package com.nicefish.web.controller;

import com.nicefish.model.User;
import com.nicefish.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * Created by kimmking on 17/6/26.
 */

@RestController
@Api("权限验证接口")
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    UserService userService;

    @ApiOperation(value = "login", nickname = "login",response = User.class)
    @ApiResponses({
            @ApiResponse(code = 404, response = String.class, message = "user not found"),
            @ApiResponse(code = 500, response = String.class, message = "Internal server error")
    })
    @RequestMapping(value = "/login",method = {RequestMethod.GET,RequestMethod.POST})
    public User login(@RequestParam String userName,@RequestParam String password,HttpSession session) {

        UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
        Subject subject = SecurityUtils.getSubject();
        subject.login(token);

        User user = userService.findByUserName(userName);
        session.setAttribute("_USERINFO",user);
        return user;
    }

    @ApiOperation(value = "register", nickname = "register",response = User.class)
    @ApiResponses({
            @ApiResponse(code = 404, response = String.class, message = "user not found"),
            @ApiResponse(code = 500, response = String.class, message = "Internal server error")
    })
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public User register(@RequestBody User user) {
        user.setUserId(java.util.UUID.randomUUID().toString());
        return userService.save(user);
    }
}
