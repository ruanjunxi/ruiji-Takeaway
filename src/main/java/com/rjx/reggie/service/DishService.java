package com.rjx.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rjx.reggie.dto.DishDto;
import com.rjx.reggie.entity.Dish;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author:
 * @Description:
 */
public interface DishService extends IService<Dish> {

    public void updateWithFlavor(DishDto dishDto);

    public void saveWithFlavor(DishDto dishDto);

    DishDto getByIdWithFlavor(Long id);
}
