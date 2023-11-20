package com.openresty.dao.service;

import java.util.Map;
import java.util.List;

import com.openresty.common.utils.PageResult;
import com.openresty.dao.entity.Topic;
/**
 * Redis Service.
 *
 * @author pdai
 */
public interface IRedisService {
    PageResult<Topic> getBlogInfoPageResultByHash(String hash, Integer pageNum);

/*
    public void set(String key, Object value, long time);

    public Object get(String key);
*/
    void saveKVToHash(String hash, Object key, Object value);

    void saveMapToHash(String hash, Map map);

    Map getMapByHash(String hash);

    Object getValueByHashKey(String hash, Object key);

    void incrementByHashKey(String hash, Object key, int increment);

    void deleteByHashKey(String hash, Object key);

    <T> List<T> getListByValue(String key);

    <T> void saveListToValue(String key, List<T> list, long ttl);

    <T> Map<String, T> getMapByValue(String key);

    <T> void saveMapToValue(String key, Map<String, T> map);

    <T> T getObjectByValue(String key, Class t);

    void incrementByKey(String key, int increment);

    void saveObjectToValue(String key, Object object, long ttl);

    void saveValueToSet(String key, Object value);

    int countBySet(String key);

    void deleteValueBySet(String key, Object value);

    boolean hasValueInSet(String key, Object value);

    void deleteCacheByKey(String key);

    boolean hasKey(String key);

    void expire(String key, long time);
}
