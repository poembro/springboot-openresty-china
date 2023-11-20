package com.openresty.dao.mapper;

import com.openresty.dao.entity.Follow;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.openresty.dao.entity.Like;
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
public interface FollowMapper extends BaseMapper<Follow> {
    //查询所有用户
    @Select("select * from follow limit #{pageNum},#{pageSize}")
    List<Follow> findList(Integer pageNum, int pageSize);

    @Select("select count(id) from follow ")
    Long findCount();
}
