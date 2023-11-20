package com.openresty.dao.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.openresty.common.utils.PageResult;
import com.openresty.dao.entity.Collect;
import com.openresty.dao.entity.Collect;
import com.openresty.dao.mapper.CollectMapper;
import com.openresty.dao.mapper.CollectMapper;
import com.openresty.dao.service.ICollectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.openresty.dao.service.IRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 我的收藏 服务实现类
 * </p>
 *
 * @author poembro
 * @since 2023-11-15
 */
@Service
public class CollectServiceImpl extends ServiceImpl<CollectMapper, Collect> implements ICollectService {
    // 缓存key
    private static final String redisCacheKeyCollect = "springboot_bbs_redis_cache_Collect:%d";
    private static final String redisCacheKeyCollects = "springboot_bbs_redis_cache_Collects:%s";
    public static  final String getRedisCacheKeyCollect(Integer id){
        return String.format(redisCacheKeyCollect, id);
    }
    public static  final String getRedisCacheKeyCollects(String key){
        return String.format(redisCacheKeyCollects, key);
    }

    @Autowired
    private CollectMapper mp ;
    @Autowired
    IRedisService redisService;


    ////////////////////
    public PageResult<Collect> findList(Integer pageNum, Integer pageSize) {
        pageNum = (pageNum - 1) * pageSize;
        List<Collect> items = mp.findList( pageNum, pageSize);
        Long count = mp.findCount();
        Integer countInt = Long.valueOf(count).intValue();
        PageResult<Collect> resp = new PageResult<>(countInt, items);
        return resp;
    }



    public Collect selectById(Integer id) {
        String key =  getRedisCacheKeyCollect(id);
        Collect itemCache = redisService.getObjectByValue(key, Collect.class);

        if (!Objects.isNull(itemCache)) {
            return itemCache;
        }

        Collect item = mp.selectById(id);
        if (!Objects.isNull(item)) {
            redisService.saveObjectToValue(key, item,60);
            return item;
        }
        return item;
    }

    // 非id条件的修改， 多条件修改查询后  修改字段
    public int UpdateByWhere(Collect item) {
        UpdateWrapper<Collect> updateWrapper = new UpdateWrapper<>();
        // updateWrapper.eq("title",item.getTitle());
        //item.setWeight(3);
        Integer rows = mp.update(item, updateWrapper);
        return rows;
    }


    public void del(Integer id){
        String key = getRedisCacheKeyCollect(id);
        redisService.deleteCacheByKey(key);
    }
}
