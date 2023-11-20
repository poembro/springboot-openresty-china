package com.openresty.dao.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.openresty.common.exception.NotFoundException;
import com.openresty.common.utils.PageResult;
import com.openresty.dao.entity.Notification;
import com.openresty.dao.entity.Notification;
import com.openresty.dao.mapper.NotificationMapper;
import com.openresty.dao.mapper.TopicMapper;
import com.openresty.dao.service.INotificationService;
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
public class NotificationServiceImpl extends ServiceImpl<NotificationMapper, Notification> implements INotificationService {

    // 缓存key
    private static final String redisCacheKeyNotification = "springboot_bbs_redis_cache_notification:%d";
    private static final String redisCacheKeyNotifications = "springboot_bbs_redis_cache_notifications:%s";
    public static  final String getRedisCacheKeyNotification(Integer id){
        return String.format(redisCacheKeyNotification, id);
    }
    public static  final String getRedisCacheKeyNotifications(String key){
        return String.format(redisCacheKeyNotifications, key);
    }

    @Autowired
    private NotificationMapper mp ;
    @Autowired
    IRedisService redisService;


    ////////////////////
    public PageResult<Notification> findList(Integer pageNum, Integer pageSize) {
        pageNum = (pageNum - 1) * pageSize;
        List<Notification> items = mp.findList( pageNum, pageSize);
        Long count = mp.findCount();
        Integer countInt = Long.valueOf(count).intValue();
        PageResult<Notification> resp = new PageResult<>(countInt, items);
        return resp;
    }



    public Notification selectById(Integer id) {
        String key =  getRedisCacheKeyNotification(id);
        Notification itemCache = redisService.getObjectByValue(key, Notification.class);

        if (!Objects.isNull(itemCache)) {
            return itemCache;
        }

        Notification item = mp.selectById(id);
        if (!Objects.isNull(item)) {
            redisService.saveObjectToValue(key, item,60);
            return item;
        }
        return item;
    }

    // 非id条件的修改， 多条件修改查询后  修改字段
    public int UpdateByWhere(Notification item) {
        UpdateWrapper<Notification> updateWrapper = new UpdateWrapper<>();
       // updateWrapper.eq("title",item.getTitle());
        //item.setWeight(3);
        Integer rows = mp.update(item, updateWrapper);
        return rows;
    }


    public void del(Integer id){
        String key = getRedisCacheKeyNotification(id);
        redisService.deleteCacheByKey(key);

    }
}
