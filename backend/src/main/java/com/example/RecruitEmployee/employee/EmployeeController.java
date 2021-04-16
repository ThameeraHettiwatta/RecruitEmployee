package com.example.RecruitEmployee.employee;

import com.example.RecruitEmployee.dto.EmployeeDto;
import com.example.RecruitEmployee.exception.ApiRequestException;
import com.github.pagehelper.PageInfo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService, ModelMapper modelMapper) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployee() {
        List<Employee> employees = employeeService.getAllEmployee();
        return new ResponseEntity<>(employees, HttpStatus.OK);
//        return employeeService.getAllEmployee();
    }

    @GetMapping(path = "{pageNo}/{pageSize}")
    public ResponseEntity<PageInfo<Employee>> getPaginatedEmployee(@PathVariable("pageNo") int pageNo, @PathVariable("pageSize") int pageSize) {
        PageInfo<Employee> pageEmployee = employeeService.getPaginatedEmployee(pageNo, pageSize);
        return new ResponseEntity<>(pageEmployee, HttpStatus.OK);
    }

    @GetMapping(path = "{empId}")
    public EmployeeDto getEmployeeById(@PathVariable("empId") Integer empId) {
//        return convertToDto(employeeService.getEmployeeById(empId).orElseThrow(() -> new ApiRequestException("User not found with empId:" + empId)));
        return employeeService.getEmployeeById(empId);
    }


    @PostMapping
    public ResponseEntity<Integer> addEmployee(@NonNull @RequestBody Employee employee) {
        int added = employeeService.addEmployee(employee);
        return new ResponseEntity<>(added, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Integer> updateEmployee(@NonNull @RequestBody Employee employee) {
        int updated = employeeService.updateEmployee(employee);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping(path = "{empId}")
    public ResponseEntity<Integer> deleteEmployee(@PathVariable("empId") Integer empId) {
        int deleted = employeeService.deleteEmployee(empId);
        return new ResponseEntity<>(deleted, HttpStatus.OK);
    }

    @GetMapping(path = "{projectId}/employees")
    public ResponseEntity<List<Employee>> getEmployeeByProjectId(@PathVariable("projectId") Integer projectId) {
       List<Employee> employees = employeeService.getEmployeeByProjectId(projectId);
       return new ResponseEntity<>(employees, HttpStatus.OK);
    }

}
