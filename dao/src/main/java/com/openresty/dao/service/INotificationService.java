package com.openresty.dao.service;

import com.openresty.common.utils.PageResult;
import com.openresty.dao.entity.Notification;
import com.baomidou.mybatisplus.extension.service.IService;
import com.openresty.dao.entity.Topic;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author poembro
 * @since 2023-11-15
 */
public interface INotificationService extends IService<Notification> {
    public PageResult<Notification> findList(Integer pageNum, Integer pageSize) ;
    public Notification selectById(Integer id);
    public void del(Integer id);
}
