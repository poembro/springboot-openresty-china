package com.openresty.dao.service;

import com.openresty.common.utils.PageResult;
import com.openresty.dao.entity.Category;
import com.baomidou.mybatisplus.extension.service.IService;
import com.openresty.dao.entity.Collect;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author poembro
 * @since 2023-11-15
 */
public interface ICategoryService extends IService<Category> {
    public PageResult<Category> findList(Integer pageNum, Integer pageSize);
    public Category selectById(Integer id);

    public void del(Integer id);
}
