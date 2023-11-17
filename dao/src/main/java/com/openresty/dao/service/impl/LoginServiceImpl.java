package com.openresty.dao.service.impl;

import com.openresty.dao.common.utils.HashUtils;
import com.openresty.dao.common.utils.JwtUtils;
import com.openresty.dao.entity.MyUserDetails;
import com.openresty.dao.entity.User;
import com.openresty.dao.mapper.UserMapper;
import com.openresty.dao.service.IRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
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
public class LoginServiceImpl implements UserDetailsService{
    private UserMapper mp;

    @Autowired //注入userMapper，从而可以在类中正常使用userMapper的方法
    public LoginServiceImpl(UserMapper mp){
        this.mp = mp;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = mp.findByUsername(username);
        if (Objects.isNull(user) ) {
            throw new UsernameNotFoundException("用户不存在");
        }

        return new MyUserDetails(user);
    }


     @Autowired
    private IRedisService<User> redisService;

    public String login(User user) {
        //authenticate存入redis
        redisService.set("login:"+8, user);
        //把token响应给前端
        // HashMap<String,String> map = new HashMap<>();
        // map.put("token",jwt);
        return "";
    }

    public boolean logout() {
        //  删除redis中的值
        redisService.delete("login:" + 8);
        return true;
    }
}
