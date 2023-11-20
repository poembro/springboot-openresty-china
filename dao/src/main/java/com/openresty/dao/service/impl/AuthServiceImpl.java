package com.openresty.dao.service.impl;

import com.openresty.dao.entity.User;
import com.openresty.dao.mapper.UserMapper;
import com.openresty.dao.service.IRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author poembro
 * @since 2023-11-15
 */

@Service
public class AuthServiceImpl implements UserDetailsService{
    private UserMapper mp;

    @Autowired //注入userMapper，从而可以在类中正常使用userMapper的方法
    public AuthServiceImpl(UserMapper mp){
        this.mp = mp;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails item = mp.findByUsername(username);
        if (Objects.isNull(item) ) {
            throw new UsernameNotFoundException("用户不存在");
        }

        return item;
    }

    public String login(User user) {
        //authenticate存入redis
        //把token响应给前端
        // HashMap<String,String> map = new HashMap<>();
        // map.put("token",jwt);
        return "";
    }

    public boolean logout() {
        //  删除redis中的值
        return true;
    }
}
