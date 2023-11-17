package com.openresty.bbs.controller;

import com.openresty.dao.common.utils.PageResult;
import com.openresty.dao.entity.Topic;
import com.openresty.dao.service.ITopicService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/v1/topic")
@RestController
public class TopicController {
    @Autowired     // 1. 字段注入
    private ITopicService svc;


    @ApiOperation("list")
    @PreAuthorize("hasAuthority('*.*.*')")
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<Topic> list(@RequestParam(defaultValue = "1") Integer pageNum){
        System.out.println(3333333);
        List<Topic> items = svc.getTopicList(pageNum);
        return items;
    }
}

