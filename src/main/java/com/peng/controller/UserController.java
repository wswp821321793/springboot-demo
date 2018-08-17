package com.peng.controller;

import com.peng.config.ErrorCode;
import com.peng.model.Response;
import com.peng.model.User;
import com.peng.request.LoginRequest;
import com.peng.request.UserRequest;
import com.peng.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@RestController
@RequestMapping("/user")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;
    //post登录

    @RequestMapping(value = "/login",method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Response login(@RequestBody LoginRequest request){
        //获取用户认证信息
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(
                request.getUsername(), request.getPassword());
        //进行验证，这里可以捕获异常，然后返回对应信息
        try {
            subject.login(usernamePasswordToken);
        } catch (UnknownAccountException e){
            logger.error(String.format("登陆账号：%s 出现异常",request.getUsername()),e);
            return new Response(ErrorCode.USER_NAME_NOT_EXIST.getCode(),ErrorCode.USER_NAME_NOT_EXIST.getMsg());
        } catch (IncorrectCredentialsException e){
            logger.error(String.format("登陆账号：%s 出现异常",request.getUsername()),e);
            return new Response(ErrorCode.USER_PASSWORD_ERROR.getCode(),ErrorCode.USER_PASSWORD_ERROR.getMsg());
        } catch (Exception e){
            logger.error(String.format("登陆账号：%s 出现异常",request.getUsername()),e);
            return new Response(ErrorCode.USER_UNKNOWN_ERROR.getCode(),ErrorCode.USER_UNKNOWN_ERROR.getMsg());
        }
        return new Response(200);
    }

    @RequestMapping(value = "/get",method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public Response userInfo(HttpServletRequest httpServletRequest){
        //获取用户认证信息
        Subject subject = SecurityUtils.getSubject();
        if(subject==null){
            logger.error("用户未登录，请登录后再试");
            return new Response(ErrorCode.USER_NOT_LOGIN.getCode(),ErrorCode.USER_NOT_LOGIN.getMsg());
        }
        User user;
        try {
            user = userService.findUserByUsername(subject.getPrincipal().toString());
        } catch (Exception e){
            logger.error("获取用户信息出现异常",e);
            return new Response(ErrorCode.USER_UNKNOWN_ERROR.getCode(),ErrorCode.USER_UNKNOWN_ERROR.getMsg());
        }
        return new Response(200,user);
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Response register(@RequestBody UserRequest request){
        User user = new User();
        user.setName(request.getName());
        user.setPhone(request.getPhone());
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setEmail(request.getPassword());
        try {
            userService.register(user);
        } catch (Exception e){
            logger.error("注册用户信息出现异常",e);
            return new Response(ErrorCode.USER_UNKNOWN_ERROR.getCode(),ErrorCode.USER_UNKNOWN_ERROR.getMsg());
        }
        return new Response(200,user);
    }
}
