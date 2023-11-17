package com.openresty.dao.service;

import com.openresty.dao.common.utils.PageResult;
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
    public List<Topic> find() ;
    public List<Topic> getTopicList(Integer pageNum);
}
