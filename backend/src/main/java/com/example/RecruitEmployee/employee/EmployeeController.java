package com.example.RecruitEmployee.employee;

import com.example.RecruitEmployee.dto.EmployeeDto;
import com.github.pagehelper.PageInfo;
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
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployee() {
        List<EmployeeDto> employees = employeeService.getAllEmployee();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping(path = "{pageNo}/{pageSize}")
    public ResponseEntity<PageInfo<EmployeeDto>> getPaginatedEmployee(@PathVariable("pageNo") int pageNo, @PathVariable("pageSize") int pageSize) {
        PageInfo<EmployeeDto> pageEmployee = employeeService.getPaginatedEmployee(pageNo, pageSize);
        return new ResponseEntity<>(pageEmployee, HttpStatus.OK);
    }

    @GetMapping(path = "{empId}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("empId") Integer empId) {
        EmployeeDto employeeDto = employeeService.getEmployeeById(empId);
        return new ResponseEntity<>(employeeDto, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<Integer> addEmployee(@NonNull @RequestBody Employee employee) {
        int added = employeeService.addEmployee(employee);
        return new ResponseEntity<>(added, HttpStatus.CREATED);
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
    public ResponseEntity<List<EmployeeDto>> getEmployeeByProjectId(@PathVariable("projectId") Integer projectId) {
       List<EmployeeDto> employees = employeeService.getEmployeeByProjectId(projectId);
       return new ResponseEntity<>(employees, HttpStatus.OK);
    }

}
