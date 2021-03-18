package com.example.RecruitEmployee.service;

import com.example.RecruitEmployee.employee.Employee;
import com.example.RecruitEmployee.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeMapper employeeMapper;

    public EmployeeService(EmployeeMapper employeeMapper) {
        this.employeeMapper = employeeMapper;
    }

    public List<Employee> getAllEmployee(){
        return employeeMapper.getAllEmployee();
    }

    public Employee getEmployeeById(Integer empId) {
        return employeeMapper.getEmployeeById(empId);
    }

    public void addEmployee(Employee employee) {
        employeeMapper.addEmployee(employee);
    }

    public void updateEmployee(Integer empId, Employee employee) {
        employeeMapper.updateEmployee(empId, employee);
    }

    public void deleteEmployee(Integer empId) {
        employeeMapper.deleteEmployee(empId);
    }

    public List<Employee> getEmployeeByProjectId(Integer projectId) {
        return employeeMapper.getEmployeeByProjectId(projectId);
    }
}
