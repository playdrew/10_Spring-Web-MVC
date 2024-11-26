package com.ohgiraffers.crud.employee.model.dao;

import com.ohgiraffers.crud.employee.model.dto.EmployeeDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmployeeMapper {
    List<EmployeeDTO> getAllEmployee();

    void deleteById(long id);
}
