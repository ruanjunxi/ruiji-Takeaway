package com.rjx.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rjx.reggie.entity.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author:
 * @Description:
 */
@Mapper
@Repository
public interface EmployeeMapper extends BaseMapper<Employee> {
}
