package com.openresty.dao.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.openresty.dao.entity.User;
import com.openresty.dao.mapper.UserMapper;
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

    public User findUserByName(String username) {
        // 根据用户名查询用户信息
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername, username);
        User item = mp.selectOne(wrapper);
        if (Objects.isNull(item)) {
            throw new UsernameNotFoundException("用户不存在");
        }
        return item;
    }


}
