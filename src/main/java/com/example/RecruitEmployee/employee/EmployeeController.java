package com.example.RecruitEmployee.employee;

import com.example.RecruitEmployee.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeMapper employeeMapper;

    public EmployeeController(EmployeeMapper employeeMapper) {
        this.employeeMapper = employeeMapper;
    }


    @GetMapping("/getAllEmployee")
    public List<Employee> getAllEmployee(){
        return employeeMapper.getAllEmployee();
    }

    @GetMapping("/getEmployee/{id}")
    public Employee getEmployeeById(@PathVariable("id") Integer id) {
        return employeeMapper.getEmployeeById(id);
    }

    @PostMapping("/addEmployee")
    public void addEmployee(@NonNull @RequestBody Employee employee){
        employeeMapper.addEmployee(employee);
    }
    @PutMapping("/updateEmployee")
    public void updateEmployee(@NonNull @RequestBody Employee employee){
        employeeMapper.updateEmployee(employee);
    }

    @DeleteMapping("/deleteEmployee/{id}")
    public void deleteEmployee(@PathVariable("id") Integer id){
        employeeMapper.deleteEmployee(id);
    }

    @GetMapping("/{projectId}/allEmployee")
    public List<Employee> getEmployeeByProjectId(@PathVariable("projectId") Integer projectId){
        return employeeMapper.getEmployeeByProjectId(projectId);
    }

}
