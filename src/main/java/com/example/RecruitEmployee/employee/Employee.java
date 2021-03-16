package com.example.RecruitEmployee.employee;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Employee {
    private String empName;
    private Integer empId;
    private Long empSalary;
    private String empEmail;
}
