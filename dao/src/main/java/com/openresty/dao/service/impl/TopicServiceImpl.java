package com.openresty.dao.service.impl;

import com.openresty.dao.entity.Topic;
import com.openresty.dao.mapper.TopicMapper;
import com.openresty.dao.service.ITopicService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 * @author poembro
 * @since 2023-11-15
 */
@Service
public class TopicServiceImpl extends ServiceImpl<TopicMapper, Topic> implements ITopicService {
    //每页显示5条
    private static final int pageSize = 5;

    @Autowired //注入userMapper，从而可以在类中正常使用userMapper的方法
    private TopicMapper mp ;

    
    public List<Topic> find() {
        return mp.find();
    }

   // @Autowired
   // IRedisService redisService;

    public List<Topic> getTopicList(Integer pageNum) {
         //添加首页缓存
         return find();
    }
}
