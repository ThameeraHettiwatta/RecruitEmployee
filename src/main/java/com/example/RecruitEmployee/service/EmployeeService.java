package com.example.RecruitEmployee.service;

import com.example.RecruitEmployee.employee.Employee;
import com.example.RecruitEmployee.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeMapper employeeMapper;

    @Autowired
    public EmployeeService(@Qualifier("EmployeeMapper") EmployeeMapper employeeMapper) {
        this.employeeMapper = employeeMapper;
    }

    public List<Employee> getAllEmployee(){
        return employeeMapper.getAllEmployee();
    }

    public Optional <Employee> getEmployeeById(Integer empId) {
        return employeeMapper.getEmployeeById(empId);
    }

    public int addEmployee(Employee employee) {
        return employeeMapper.addEmployee(employee);
    }

    public int updateEmployee(Integer empId, Employee employee) {
        return employeeMapper.updateEmployee(empId, employee);
    }

    public int deleteEmployee(Integer empId) {
        return employeeMapper.deleteEmployee(empId);
    }

    public List<Employee> getEmployeeByProjectId(Integer projectId) {
        return employeeMapper.getEmployeeByProjectId(projectId);
    }
}
