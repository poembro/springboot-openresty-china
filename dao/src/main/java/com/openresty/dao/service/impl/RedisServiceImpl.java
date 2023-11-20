package com.openresty.dao.service.impl;

import com.openresty.common.utils.JacksonUtils;
import com.openresty.common.utils.PageResult;
import com.openresty.dao.service.IRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


import com.openresty.dao.entity.Topic;
/**
 * @Description: 读写Redis相关操作
 * @Author: Naccl
 * @Date: 2020-09-27
 */
@Service
public class RedisServiceImpl implements IRedisService {

    @Autowired
    RedisTemplate jsonRedisTemplate;

    @Override
    public PageResult<Topic> getBlogInfoPageResultByHash(String hash, Integer pageNum) {
        if (jsonRedisTemplate.opsForHash().hasKey(hash, pageNum)) {
            Object redisResult = jsonRedisTemplate.opsForHash().get(hash, pageNum);
            PageResult<Topic> pageResult = JacksonUtils.convertValue(redisResult, PageResult.class);
            return pageResult;
        } else {
            return null;
        }
    }

/*
    @Override
    public void set(String key, Object value, long time) {
        jsonRedisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
    }



    @Override
    public Object get(String key) {
        return jsonRedisTemplate.opsForValue().get(key);
    }
*/

    // hash 结构中的 某个key 自增 比如 浏览量
    @Override
    public void saveKVToHash(String hash, Object key, Object value) {
        jsonRedisTemplate.opsForHash().put(hash, key, value);
    }

    // hash 结构中的 某个key 自增 比如 浏览量
    @Override
    public Object getValueByHashKey(String hash, Object key) {
        return jsonRedisTemplate.opsForHash().get(hash, key);
    }

    @Override
    public void saveMapToHash(String hash, Map map) {
        jsonRedisTemplate.opsForHash().putAll(hash, map);
    }

    @Override
    public Map getMapByHash(String hash) {
        return jsonRedisTemplate.opsForHash().entries(hash);
    }

    // hash 结构中的 某个key 自增 比如 浏览量
    @Override
    public void incrementByHashKey(String hash, Object key, int increment) {
        if (increment < 0) {
            throw new RuntimeException("递增因子必须大于0");
        }
        jsonRedisTemplate.opsForHash().increment(hash, key, increment);
    }

    @Override
    public void deleteByHashKey(String hash, Object key) {
        jsonRedisTemplate.opsForHash().delete(hash, key);
    }

    @Override
    public <T> List<T> getListByValue(String key) {
        List<T> redisResult = (List<T>) jsonRedisTemplate.opsForValue().get(key);
        return redisResult;
    }

    @Override
    public <T> void saveListToValue(String key, List<T> list, long ttl) {
        jsonRedisTemplate.opsForValue().set(key, list, ttl,TimeUnit.SECONDS);
    }

    @Override
    public <T> Map<String, T> getMapByValue(String key) {
        Map<String, T> redisResult = (Map<String, T>) jsonRedisTemplate.opsForValue().get(key);
        return redisResult;
    }

    @Override
    public <T> void saveMapToValue(String key, Map<String, T> map) {
        jsonRedisTemplate.opsForValue().set(key, map);
    }

    @Override
    public <T> T getObjectByValue(String key, Class t) {
        Object redisResult = jsonRedisTemplate.opsForValue().get(key);
        T object = (T) JacksonUtils.convertValue(redisResult, t);
        return object;
    }

    @Override
    public void incrementByKey(String key, int increment) {
        if (increment < 0) {
            throw new RuntimeException("递增因子必须大于0");
        }
        jsonRedisTemplate.opsForValue().increment(key, increment);
    }

    @Override
    public void saveObjectToValue(String key, Object object, long ttl) {
        jsonRedisTemplate.opsForValue().set(key, object, ttl,TimeUnit.SECONDS);
    }

    @Override
    public void saveValueToSet(String key, Object value) {
        jsonRedisTemplate.opsForSet().add(key, value);
    }

    @Override
    public int countBySet(String key) {
        return jsonRedisTemplate.opsForSet().size(key).intValue();
    }

    @Override
    public void deleteValueBySet(String key, Object value) {
        jsonRedisTemplate.opsForSet().remove(key, value);
    }

    @Override
    public boolean hasValueInSet(String key, Object value) {
        return jsonRedisTemplate.opsForSet().isMember(key, value);
    }

    @Override
    public void deleteCacheByKey(String key) {
        jsonRedisTemplate.delete(key);
    }

    @Override
    public boolean hasKey(String key) {
        return jsonRedisTemplate.hasKey(key);
    }

    @Override
    public void expire(String key, long time) {
        jsonRedisTemplate.expire(key, time, TimeUnit.SECONDS);
    }
}
