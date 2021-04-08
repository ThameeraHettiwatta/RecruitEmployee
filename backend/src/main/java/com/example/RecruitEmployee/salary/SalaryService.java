package com.example.RecruitEmployee.salary;

import com.example.RecruitEmployee.employee.Employee;
import com.example.RecruitEmployee.mapper.EmployeeMapper;
import com.example.RecruitEmployee.mapper.ProjectMapper;
import com.example.RecruitEmployee.mapper.SalaryMapper;
import com.example.RecruitEmployee.project.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
public class SalaryService {
    private final SalaryMapper salaryMapper;
    private final EmployeeMapper employeeMapper;

    @Autowired
    public SalaryService(@Qualifier("SalaryMapper") SalaryMapper salaryMapper,@Qualifier("EmployeeMapper") EmployeeMapper employeeMapper) {
        this.salaryMapper = salaryMapper;
        this.employeeMapper = employeeMapper;
    }

    public long getProjectCost(int projectId){
        long projectCost = 0;
        List<Employee> employees = employeeMapper.getEmployeeByProjectId(projectId);
        Iterator<Employee> employeeIterator = employees.iterator();
        while (employeeIterator.hasNext()){
            projectCost = projectCost + employeeIterator.next().getEmpSalary();
        }
        return projectCost;
    }
}
