package com.openresty.bbs.controller;

import com.openresty.dao.common.utils.ResponseUtil;
import com.openresty.dao.service.IRedisService;
import com.openresty.dao.service.ITopicService;
import com.openresty.dao.service.IUserService;
import com.openresty.dao.service.impl.LoginServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.openresty.dao.entity.User;

import java.util.List;


@RestController
public class IndexController {
    // @Autowired(required = false) // 修改默认值，允许为空

    private IRedisService<User> redisService;
    @Autowired    // 2. 构造器注入
    public IndexController (IRedisService<User> bean) {
        this.redisService = bean;
    }

    private ITopicService topicSvc;
    @Autowired   // 3. setter注入
    public void setRedisService(ITopicService svc) {
        this.topicSvc = svc;
    }

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    // 登录   1.先注入 2.调用登录
    @Autowired
    LoginServiceImpl loginService;

    @Autowired     // 1. 字段注入
    private IUserService userService;
    @ApiOperation("login 获取token")
    @GetMapping("/login")
    public ResponseEntity<String> login(@RequestParam(defaultValue = "1") Integer userId) {
        User user = userService.getById(userId);
        String newPwd =  passwordEncoder.encode(user.getPassword());
        user.setPassword(newPwd);
        userService.updateById(user);

        String item = loginService.login(user);
        return new ResponseEntity<>("hello world " + item, HttpStatus.OK);
    }








    @ApiOperation("hello")
    @GetMapping("/hello")
    public String sayHello(@RequestParam(required = false, name = "who2") String who) {
        return who;
    }



    // 测试redis
    /**
     * @param user user param
     * @return user
     */
    @ApiOperation("Redis 测试get")
    @PostMapping("/add")
    public ResponseUtil<User> add(User user) {
        redisService.set(String.valueOf(user.getId()), user);
        return ResponseUtil.success(redisService.get(String.valueOf(user.getId())));
    }

    /**
     * @return user list
     */
    @ApiOperation("Redis 测试get")
    @GetMapping("/find/{userId}")
    public ResponseUtil<User> edit(@PathVariable("userId") String userId) {
        return ResponseUtil.success(redisService.get(userId));
    }





}
