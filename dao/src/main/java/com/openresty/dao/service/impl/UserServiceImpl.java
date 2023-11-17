package com.openresty.dao.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.openresty.dao.entity.User;
import com.openresty.dao.mapper.UserMapper;
import com.openresty.dao.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Objects;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author poembro  implements UserService, UserDetailsService {
 * @since 2023-11-15
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService  {

    @Autowired //注入userMapper，从而可以在类中正常使用userMapper的方法
    private UserMapper mp ;

    public User findUserById(Long id) {
        User item = mp.findById(id);
        if (Objects.isNull(item)) {
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

    public User FindOne(Long id) {
        return mp.selectById(id);
    }

    public int Create(User item) {
        return mp.insert(item);
    }

    public int Update(User item) {
        return mp.updateById(item);
    }

    public int UpdateByWhere(User item) {
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("name",item.getUsername());
        item.setWebsite("ssss");

        Integer rows = mp.update(item, updateWrapper);
        return rows;
    }


}
