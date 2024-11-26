package com.ohgiraffers.crud.employee.model.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class EmployeeDTO{

    private long id;
    private String name;
    private String email;


}