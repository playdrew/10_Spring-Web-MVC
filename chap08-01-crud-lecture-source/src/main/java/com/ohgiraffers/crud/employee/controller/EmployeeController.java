package com.ohgiraffers.crud.employee.controller;

import com.ohgiraffers.crud.employee.model.dto.EmployeeDTO;
import com.ohgiraffers.crud.employee.model.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/employee/*")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("list")
    public String listPage(Model model) {
        List<EmployeeDTO> employees = employeeService.getAllEmployee();

        model.addAttribute("employees", employees);
        return "employee/list";
    }

    @PostMapping("delete")
    public String deleteSelectedEmployees(@RequestParam List<Long> selectedEmployees) {
        if (selectedEmployees != null && !selectedEmployees.isEmpty()) {
            employeeService.deleteEmployeesByIds(selectedEmployees);
        }
        return "redirect:/employee/list";
    }
}
