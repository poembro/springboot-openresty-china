package com.openresty.dao.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.openresty.common.utils.PageResult;
import com.openresty.dao.entity.Follow;
import com.openresty.dao.entity.Follow;
import com.openresty.dao.mapper.FollowMapper;
import com.openresty.dao.mapper.FollowMapper;
import com.openresty.dao.service.IFollowService;
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
public class FollowServiceImpl extends ServiceImpl<FollowMapper, Follow> implements IFollowService {

    // 缓存key
    private static final String redisCacheKeyFollow = "springboot_bbs_redis_cache_Followe:%d";
    private static final String redisCacheKeyFollows = "springboot_bbs_redis_cache_Followes:%s";
    public static  final String getRedisCacheKeyFollow(Integer id){
        return String.format(redisCacheKeyFollow, id);
    }
    public static  final String getRedisCacheKeyFollows(String key){
        return String.format(redisCacheKeyFollows, key);
    }

    @Autowired
    private FollowMapper mp ;
    @Autowired
    IRedisService redisService;


    ////////////////////
    public PageResult<Follow> findList(Integer pageNum, Integer pageSize) {
        pageNum = (pageNum - 1) * pageSize;
        List<Follow> items = mp.findList( pageNum, pageSize);
        Long count = mp.findCount();
        Integer countInt = Long.valueOf(count).intValue();
        PageResult<Follow> resp = new PageResult<>(countInt, items);
        return resp;
    }



    public Follow selectById(Integer id) {
        String key =  getRedisCacheKeyFollow(id);
        Follow itemCache = redisService.getObjectByValue(key, Follow.class);

        if (!Objects.isNull(itemCache)) {
            return itemCache;
        }

        Follow item = mp.selectById(id);
        if (!Objects.isNull(item)) {
            redisService.saveObjectToValue(key, item,60);
            return item;
        }
        return item;
    }

    // 非id条件的修改， 多条件修改查询后  修改字段
    public int UpdateByWhere(Follow item) {
        UpdateWrapper<Follow> updateWrapper = new UpdateWrapper<>();
        // updateWrapper.eq("title",item.getTitle());
        //item.setWeight(3);
        Integer rows = mp.update(item, updateWrapper);
        return rows;
    }


    public void del(Integer id){
        String key = getRedisCacheKeyFollow(id);
        redisService.deleteCacheByKey(key);

    }
}
