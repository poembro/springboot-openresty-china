package com.openresty.dao.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.openresty.common.utils.PageResult;
import com.openresty.dao.entity.Like;
import com.openresty.dao.entity.Like;
import com.openresty.dao.entity.Notification;
import com.openresty.dao.mapper.LikeMapper;
import com.openresty.dao.mapper.NotificationMapper;
import com.openresty.dao.service.ILikeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.openresty.dao.service.IRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author poembro
 * @since 2023-11-15
 */
@Service
public class LikeServiceImpl extends ServiceImpl<LikeMapper, Like> implements ILikeService {

    // 缓存key
    private static final String redisCacheKeyLike = "springboot_bbs_redis_cache_like:%d";
    private static final String redisCacheKeyLikes = "springboot_bbs_redis_cache_likes:%s";
    public static  final String getRedisCacheKeyLike(Integer id){
        return String.format(redisCacheKeyLike, id);
    }
    public static  final String getRedisCacheKeyLikes(String key){
        return String.format(redisCacheKeyLikes, key);
    }

    @Autowired
    private LikeMapper mp ;
    @Autowired
    IRedisService redisService;


    ////////////////////
    public PageResult<Like> findList(Integer pageNum, Integer pageSize) {
        pageNum = (pageNum - 1) * pageSize;
        List<Like> items = mp.findList( pageNum, pageSize);
        Long count = mp.findCount();
        Integer countInt = Long.valueOf(count).intValue();
        PageResult<Like> resp = new PageResult<>(countInt, items);
        return resp;
    }



    public Like selectById(Integer id) {
        String key =  getRedisCacheKeyLike(id);
        Like itemCache = redisService.getObjectByValue(key, Like.class);

        if (!Objects.isNull(itemCache)) {
            return itemCache;
        }

        Like item = mp.selectById(id);
        if (!Objects.isNull(item)) {
            redisService.saveObjectToValue(key, item,60);
            return item;
        }
        return item;
    }

    // 非id条件的修改， 多条件修改查询后  修改字段
    public int UpdateByWhere(Like item) {
        UpdateWrapper<Like> updateWrapper = new UpdateWrapper<>();
        // updateWrapper.eq("title",item.getTitle());
        //item.setWeight(3);
        Integer rows = mp.update(item, updateWrapper);
        return rows;
    }


    public void del(Integer id){
        String key = getRedisCacheKeyLike(id);
        redisService.deleteCacheByKey(key);

    }
}
