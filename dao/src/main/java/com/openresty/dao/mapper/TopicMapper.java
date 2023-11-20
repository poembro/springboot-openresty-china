package com.openresty.dao.mapper;

import com.openresty.dao.entity.Topic;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
public interface TopicMapper extends BaseMapper<Topic> {
    //查询所有用户
    @Select("select * from topic limit #{pageNum},#{pageSize}")
    List<Topic> findList(Integer pageNum, int pageSize);

    @Select("select count(id) from topic ")
    Long findCount();


}
