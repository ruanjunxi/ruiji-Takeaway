package com.rjx.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rjx.reggie.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User>{
}
