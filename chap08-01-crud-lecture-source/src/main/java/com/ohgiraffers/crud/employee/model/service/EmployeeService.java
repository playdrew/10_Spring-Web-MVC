package com.ohgiraffers.crud.employee.model.service;

import com.ohgiraffers.crud.employee.model.dao.EmployeeMapper;
import com.ohgiraffers.crud.employee.model.dto.EmployeeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeMapper employeeMapper;

    @Autowired
    public EmployeeService(EmployeeMapper employeeMapper) {
        this.employeeMapper = employeeMapper;
    }

    public List<EmployeeDTO> getAllEmployee() {
        return employeeMapper.getAllEmployee();
    }

    public void deleteEmployeesByIds(List<Long> selectedEmployees) {
        for (long id : selectedEmployees) {
            employeeMapper.deleteById(id);
        }
    }
}

