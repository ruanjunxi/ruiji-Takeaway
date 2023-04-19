package com.rjx.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rjx.reggie.entity.Category;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author:
 * @Description:
 */
public interface CategoryService extends IService<Category> {
    public void remove(Long id);
}
