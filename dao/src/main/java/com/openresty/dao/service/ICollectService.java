package com.openresty.dao.service;

import com.openresty.common.utils.PageResult;
import com.openresty.dao.entity.Collect;
import com.baomidou.mybatisplus.extension.service.IService;
import com.openresty.dao.entity.Comment;

/**
 * <p>
 * 我的收藏 服务类
 * </p>
 *
 * @author poembro
 * @since 2023-11-15
 */
public interface ICollectService extends IService<Collect> {
    public PageResult<Collect> findList(Integer pageNum, Integer pageSize);
    public Collect selectById(Integer id);

    public void del(Integer id);
}
