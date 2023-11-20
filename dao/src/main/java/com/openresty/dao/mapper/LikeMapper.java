package com.openresty.dao.mapper;

import com.openresty.dao.entity.Like;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.openresty.dao.entity.Notification;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author poembro
 * @since 2023-11-15
 */
@Mapper
public interface LikeMapper extends BaseMapper<Like> {
    //查询所有用户
    @Select("select * from like limit #{pageNum},#{pageSize}")
    List<Like> findList(Integer pageNum, int pageSize);

    @Select("select count(id) from like ")
    Long findCount();
}
