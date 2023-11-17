package com.openresty.dao.service.impl;

import com.openresty.dao.entity.Category;
import com.openresty.dao.mapper.CategoryMapper;
import com.openresty.dao.service.ICategoryService;
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
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {

}
