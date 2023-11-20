package com.openresty.dao.service;

import com.openresty.common.utils.PageResult;
import com.openresty.dao.entity.Comment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.openresty.dao.entity.Follow;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author poembro
 * @since 2023-11-15
 */
public interface ICommentService extends IService<Comment> {
    public PageResult<Comment> findList(Integer pageNum, Integer pageSize);
    public Comment selectById(Integer id);

    public void del(Integer id);
}
