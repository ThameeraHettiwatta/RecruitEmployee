package com.example.RecruitEmployee.salary;
import com.example.RecruitEmployee.employee.Employee;
import com.example.RecruitEmployee.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
public class SalaryService {
    private final EmployeeMapper employeeMapper;

    @Autowired
    public SalaryService(@Qualifier("EmployeeMapper") EmployeeMapper employeeMapper) {
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
