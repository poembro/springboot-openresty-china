package com.openresty.dao.service;

import com.openresty.common.utils.PageResult;
import com.openresty.dao.entity.Follow;
import com.baomidou.mybatisplus.extension.service.IService;
import com.openresty.dao.entity.Like;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author poembro
 * @since 2023-11-15
 */
public interface IFollowService extends IService<Follow> {
    public PageResult<Follow> findList(Integer pageNum, Integer pageSize);
    public Follow selectById(Integer id);

    public void del(Integer id);
}
