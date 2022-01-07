package com.example.RecruitEmployee.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeDto {
    private Integer empId;
    private String empName;
    private Long empSalary;
    private Integer projectId;
    private String empEmail;
}
