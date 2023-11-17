package com.openresty.dao.service.impl;

import com.openresty.dao.entity.Comment;
import com.openresty.dao.mapper.CommentMapper;
import com.openresty.dao.service.ICommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author poembro
 * @since 2023-11-15
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {

}
