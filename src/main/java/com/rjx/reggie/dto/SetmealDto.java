package com.rjx.reggie.dto;

import com.rjx.reggie.entity.Setmeal;
import com.rjx.reggie.entity.SetmealDish;
import lombok.Data;
import java.util.List;

@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;
}
