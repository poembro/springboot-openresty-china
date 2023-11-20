package com.openresty.dao.mapper;

import com.openresty.dao.entity.Notification;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.openresty.dao.entity.Topic;
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
public interface NotificationMapper extends BaseMapper<Notification> {
    //查询所有用户
    @Select("select * from notification limit #{pageNum},#{pageSize}")
    List<Notification> findList(Integer pageNum, int pageSize);

    @Select("select count(id) from notification ")
    Long findCount();

}
