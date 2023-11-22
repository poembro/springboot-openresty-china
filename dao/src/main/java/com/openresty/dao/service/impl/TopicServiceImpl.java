package com.openresty.dao.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.openresty.common.utils.PageResult;
import com.openresty.dao.entity.Topic;
import com.openresty.dao.mapper.TopicMapper;
import com.openresty.dao.service.IRedisService;
import com.openresty.dao.service.ITopicService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import com.openresty.common.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * <p>
 *  服务实现类
 * </p>
 * @author poembro
 * @since 2023-11-15
 */
@Service
public class TopicServiceImpl extends ServiceImpl<TopicMapper, Topic> implements ITopicService {
    // 缓存key
    private static final String redisCacheKeyTopic = "springboot_bbs_redis_cache_topic:%d";
    private static final String redisCacheKeyTopics = "springboot_bbs_redis_cache_topics:%s";
    public static  final String getRedisCacheKeyTopic(Integer id){
        return String.format(redisCacheKeyTopic, id);
    }
    public static  final String getRedisCacheKeyTopics(String key){
        return String.format(redisCacheKeyTopics, key);
    }

    @Autowired //注入userMapper，从而可以在类中正常使用userMapper的方法
    private TopicMapper mp ;
    @Autowired
    IRedisService redisService;
    public List<Topic> findListByTitle(String title) {
        List<Topic> items =  redisService.getListByValue(getRedisCacheKeyTopics(title));
        if (!Objects.isNull(items)) {
            return items;
        }

        // 根据用户名查询用户信息
        LambdaQueryWrapper<Topic> wrapper = new LambdaQueryWrapper<>();
        wrapper.likeRight(Topic::getTitle, title);
        items = mp.selectList(wrapper);
        if (Objects.isNull(items)) {
            throw new NotFoundException("不存在");
        }

        redisService.saveListToValue(getRedisCacheKeyTopics(title), items,60);
        return items;
    }


     ////////////////////

    public PageResult<Topic> findList(Integer pageNum, Integer pageSize) {
        pageNum = (pageNum - 1) * pageSize;
        List<Topic> items = mp.findList( pageNum, pageSize);

        Long count = mp.findCount();
        Integer countInt = Long.valueOf(count).intValue();
        PageResult<Topic> resp = new PageResult<>(countInt, items);
        return resp;
    }



    public Topic selectById(Integer id) {
        String key =  getRedisCacheKeyTopic(id);
        Topic itemCache = redisService.getObjectByValue(key, Topic.class);

        if (!Objects.isNull(itemCache)) {
            return itemCache;
        }

        Topic item = mp.selectById(id);
        if (!Objects.isNull(item)) {
            redisService.saveObjectToValue(key, item,60);
            return item;
        }
        return item;
    }

    // 非id条件的修改， 多条件修改查询后  修改字段
    public int UpdateByWhere(Topic item) {
        UpdateWrapper<Topic> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("title",item.getTitle());
        item.setWeight(3);
        Integer rows = mp.update(item, updateWrapper);
        return rows;
    }


    public void del(Integer id){
        String key = getRedisCacheKeyTopic(id);
        redisService.deleteCacheByKey(key);

    }
}
