package com.openresty.bbs.controller;

import com.openresty.common.utils.Result;
import com.openresty.dao.entity.User;
import com.openresty.dao.service.IRedisService;
import com.openresty.dao.service.ITopicService;
import com.openresty.dao.service.IUserService;
import com.openresty.dao.service.impl.AuthServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
public class IndexController {
    // @Autowired(required = false) // 修改默认值，允许为空

    private IRedisService redisService;
    @Autowired    // 2. 构造器注入
    public IndexController (IRedisService bean) {
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
    AuthServiceImpl authService;

    @Autowired     // 1. 字段注入
    private IUserService userService;


    @ApiOperation("login 获取token")
    @GetMapping("/login")
    public Result login(@RequestParam(defaultValue = "1") Integer userId) {
        User item = userService.getById(userId);
        String newPwd =  passwordEncoder.encode("123456");
        item.setPassword(newPwd);
        userService.updateById(item);  // 重置密码加密方式

        User resp = authService.findUserByName(item.getUsername());
        return  Result.ok("hello world " , resp);
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
    public Result add(User user) {
        //redisService.set(String.valueOf(user.getId()), user);
        return Result.ok("获取成功");
    }

    /**
     * @return user list
     */
    @ApiOperation("Redis 测试get")
    @GetMapping("/find/{userId}")
    public Result edit(@PathVariable("userId") String userId) {
        return Result.ok("获取成功");
    }

}
