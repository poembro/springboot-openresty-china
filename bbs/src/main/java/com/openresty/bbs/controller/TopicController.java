package com.openresty.bbs.controller;

import com.openresty.common.utils.PageResult;
import com.openresty.common.utils.Result;
import com.openresty.dao.entity.Topic;
import com.openresty.dao.service.ITopicService;
import io.swagger.annotations.ApiOperation;
import net.sf.jsqlparser.statement.select.Top;
import org.springframework.beans.factory.annotation.Autowired;
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


    @ApiOperation("findListByTitle")
    @RequestMapping(value = "/findListByTitle",method = RequestMethod.GET)
    public Result fundOne(@RequestParam(defaultValue = "1") String title ){
        List<Topic> items = svc.findListByTitle(title);
        return Result.ok("请求成功", items);
    }

    @ApiOperation("list")
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public Result list(@RequestParam(defaultValue = "1") Integer pageNum,
                            @RequestParam(defaultValue = "5") Integer pageSize){
        PageResult<Topic> items = svc.findList(pageNum, pageSize);
        return Result.ok("请求成功", items);
    }


    @ApiOperation("selectById")
    @RequestMapping(value = "/selectById",method = RequestMethod.GET)
    public Result selectById(@RequestParam(defaultValue = "1") Integer id ){
        Topic items = svc.selectById(id);
        return Result.ok("请求成功", items);
    }


    @ApiOperation("delete")
    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public Result del(@RequestParam(defaultValue = "1") Integer id ){
        svc.del(id);
        return Result.ok("请求成功");
    }

}

