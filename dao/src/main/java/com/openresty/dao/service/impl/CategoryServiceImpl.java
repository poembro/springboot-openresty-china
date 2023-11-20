package com.openresty.dao.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.openresty.common.utils.PageResult;
import com.openresty.dao.entity.Category;
import com.openresty.dao.entity.Category;
import com.openresty.dao.mapper.CategoryMapper;
import com.openresty.dao.mapper.CategoryMapper;
import com.openresty.dao.service.ICategoryService;
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
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {
    // 缓存key
    private static final String redisCacheKeyCategory = "springboot_bbs_redis_cache_Category:%d";
    private static final String redisCacheKeyCategorys = "springboot_bbs_redis_cache_Categorys:%s";
    public static  final String getRedisCacheKeyCategory(Integer id){
        return String.format(redisCacheKeyCategory, id);
    }
    public static  final String getRedisCacheKeyCategorys(String key){
        return String.format(redisCacheKeyCategorys, key);
    }

    @Autowired
    private CategoryMapper mp ;
    @Autowired
    IRedisService redisService;


    ////////////////////
    public PageResult<Category> findList(Integer pageNum, Integer pageSize) {
        pageNum = (pageNum - 1) * pageSize;
        List<Category> items = mp.findList( pageNum, pageSize);
        Long count = mp.findCount();
        Integer countInt = Long.valueOf(count).intValue();
        PageResult<Category> resp = new PageResult<>(countInt, items);
        return resp;
    }



    public Category selectById(Integer id) {
        String key =  getRedisCacheKeyCategory(id);
        Category itemCache = redisService.getObjectByValue(key, Category.class);

        if (!Objects.isNull(itemCache)) {
            return itemCache;
        }

        Category item = mp.selectById(id);
        if (!Objects.isNull(item)) {
            redisService.saveObjectToValue(key, item,60);
            return item;
        }
        return item;
    }

    // 非id条件的修改， 多条件修改查询后  修改字段
    public int UpdateByWhere(Category item) {
        UpdateWrapper<Category> updateWrapper = new UpdateWrapper<>();
        // updateWrapper.eq("title",item.getTitle());
        //item.setWeight(3);
        Integer rows = mp.update(item, updateWrapper);
        return rows;
    }


    public void del(Integer id){
        String key = getRedisCacheKeyCategory(id);
        redisService.deleteCacheByKey(key);
    }
}
