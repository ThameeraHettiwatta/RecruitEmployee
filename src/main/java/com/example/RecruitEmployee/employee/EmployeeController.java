package com.example.RecruitEmployee.employee;

import com.example.RecruitEmployee.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeMapper employeeMapper;

    public EmployeeController(EmployeeMapper employeeMapper) {
        this.employeeMapper = employeeMapper;
    }

    @GetMapping("/getAllEmployee")
    public List<Employee> getAllEmployees(){
        return employeeMapper.findAllEmployees();
    }

    @GetMapping("/getEmployee/{id}")
    public List<Employee> getEmployeeById(@PathVariable("id") Integer id) {
        return employeeMapper.getEmployeeById(id);
    }
}
