package com.openresty.dao.mapper;

import com.openresty.dao.entity.Collect;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.openresty.dao.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 我的收藏 Mapper 接口
 * </p>
 *
 * @author poembro
 * @since 2023-11-15
 */
@Mapper
public interface CollectMapper extends BaseMapper<Collect> {
    //查询所有用户
    @Select("select * from collect limit #{pageNum},#{pageSize}")
    List<Collect> findList(Integer pageNum, int pageSize);

    @Select("select count(id) from collect ")
    Long findCount();
}
