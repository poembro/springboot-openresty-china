package com.openresty.dao.service;

import com.openresty.common.utils.PageResult;
import com.openresty.dao.entity.Topic;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author poembro
 * @since 2023-11-15
 */
public interface ITopicService extends IService<Topic> {
    public PageResult<Topic> findList(Integer pageNum, Integer pageSize) ;
    public List<Topic> findListByTitle(String title);
    public Topic selectById(Integer id);
    public void del(Integer id);
}
