package com.openresty.dao.mapper;

import com.openresty.dao.entity.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.openresty.dao.entity.Follow;
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
public interface CommentMapper extends BaseMapper<Comment> {
    //查询所有用户
    @Select("select * from comment limit #{pageNum},#{pageSize}")
    List<Comment> findList(Integer pageNum, int pageSize);

    @Select("select count(id) from comment ")
    Long findCount();
}
