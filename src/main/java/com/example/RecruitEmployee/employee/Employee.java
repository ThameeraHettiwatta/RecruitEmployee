package com.example.RecruitEmployee.employee;

import com.example.RecruitEmployee.project.Project;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Employee {
    private Integer empId;
    private String empName;
    private Long empSalary;
    private Integer projectId;
    private String empEmail;

}
