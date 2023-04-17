package com.rjx.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rjx.reggie.entity.Employee;
import com.rjx.reggie.mapper.EmployeeMapper;
import com.rjx.reggie.service.EmployeeService;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author:
 * @Description:
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {
}
