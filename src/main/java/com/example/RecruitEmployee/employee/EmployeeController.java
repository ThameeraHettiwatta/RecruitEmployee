package com.example.RecruitEmployee.employee;

import com.example.RecruitEmployee.mapper.EmployeeMapper;
import com.example.RecruitEmployee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

//    private final EmployeeMapper employeeMapper;
//
//    public EmployeeController(EmployeeMapper employeeMapper) {
//        this.employeeMapper = employeeMapper;
//    }


    @GetMapping
    public List<Employee> getAllEmployee(){
        return employeeService.getAllEmployee();
    }

    @GetMapping(path = "{empId}")
    public Employee getEmployeeById(@PathVariable("empId") Integer empId) {
        return employeeService.getEmployeeById(empId);
    }

    @PostMapping
    public void addEmployee(@NonNull @RequestBody Employee employee){
        employeeService.addEmployee(employee);
    }

    @PutMapping(path = "{empId}")
    public void updateEmployee(@NonNull @PathVariable("empId") Integer empId, @RequestBody Employee employee){
        employeeService.updateEmployee(empId, employee);
    }

    @DeleteMapping(path = "{empId}")
    public void deleteEmployee(@PathVariable("empId") Integer empId){
        employeeService.deleteEmployee(empId);
    }

    @GetMapping(path = "{projectId}/employees")
    public List<Employee> getEmployeeByProjectId(@PathVariable("projectId") Integer projectId){
        return employeeService.getEmployeeByProjectId(projectId);
    }

}
