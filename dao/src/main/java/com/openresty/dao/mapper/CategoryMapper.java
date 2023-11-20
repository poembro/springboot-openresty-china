package com.openresty.dao.mapper;

import com.openresty.dao.entity.Category;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.openresty.dao.entity.Collect;
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
public interface CategoryMapper extends BaseMapper<Category> {
    //查询所有用户
    @Select("select * from category limit #{pageNum},#{pageSize}")
    List<Category> findList(Integer pageNum, int pageSize);

    @Select("select count(id) from category ")
    Long findCount();
}
