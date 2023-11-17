package com.openresty.bbs.controller;


import com.openresty.dao.entity.User;
import com.openresty.dao.service.IUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author poembro
 * @since 2023-11-15
 */
@Controller
@RequestMapping("/v1/user")
public class UserController {
    @Autowired     // 1. 字段注入
    private IUserService svc;

    @ApiOperation("list")
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<User> getList(String userId){
        List<User> items = svc.list();
        return items;
    }
}

