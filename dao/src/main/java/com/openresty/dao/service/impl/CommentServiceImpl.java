package com.openresty.dao.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.openresty.common.utils.PageResult;
import com.openresty.dao.entity.Comment;
import com.openresty.dao.entity.Comment;
import com.openresty.dao.mapper.CommentMapper;
import com.openresty.dao.mapper.CommentMapper;
import com.openresty.dao.service.ICommentService;
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
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {
    // 缓存key
    private static final String redisCacheKeyComment = "springboot_bbs_redis_cache_Comment:%d";
    private static final String redisCacheKeyComments = "springboot_bbs_redis_cache_Comments:%s";
    public static  final String getRedisCacheKeyComment(Integer id){
        return String.format(redisCacheKeyComment, id);
    }
    public static  final String getRedisCacheKeyComments(String key){
        return String.format(redisCacheKeyComments, key);
    }

    @Autowired
    private CommentMapper mp ;
    @Autowired
    IRedisService redisService;


    ////////////////////
    public PageResult<Comment> findList(Integer pageNum, Integer pageSize) {
        pageNum = (pageNum - 1) * pageSize;
        List<Comment> items = mp.findList( pageNum, pageSize);
        Long count = mp.findCount();
        Integer countInt = Long.valueOf(count).intValue();
        PageResult<Comment> resp = new PageResult<>(countInt, items);
        return resp;
    }



    public Comment selectById(Integer id) {
        String key =  getRedisCacheKeyComment(id);
        Comment itemCache = redisService.getObjectByValue(key, Comment.class);

        if (!Objects.isNull(itemCache)) {
            return itemCache;
        }

        Comment item = mp.selectById(id);
        if (!Objects.isNull(item)) {
            redisService.saveObjectToValue(key, item,60);
            return item;
        }
        return item;
    }

    // 非id条件的修改， 多条件修改查询后  修改字段
    public int UpdateByWhere(Comment item) {
        UpdateWrapper<Comment> updateWrapper = new UpdateWrapper<>();
        // updateWrapper.eq("title",item.getTitle());
        //item.setWeight(3);
        Integer rows = mp.update(item, updateWrapper);
        return rows;
    }


    public void del(Integer id){
        String key = getRedisCacheKeyComment(id);
        redisService.deleteCacheByKey(key);
    }
}
