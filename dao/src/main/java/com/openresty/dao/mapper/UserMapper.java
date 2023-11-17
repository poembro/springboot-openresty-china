package com.openresty.dao.mapper;

import com.openresty.dao.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author poembro
 * @since 2023-11-15
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Select("select * from user where username = #{username} limit 1")
    User findByUsername(String username);


    @Select("select * from user where id = #{id} limit 1")
    User findById(Long id);


     int updateUserByUsername(String username,User user);


}
