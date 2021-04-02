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

    private final ModelMapper modelMapper;

    @Autowired
    public EmployeeController(EmployeeService employeeService, ModelMapper modelMapper) {
        this.employeeService = employeeService;
        this.modelMapper = modelMapper;
    }

//    private final EmployeeMapper employeeMapper;
//
//    public EmployeeController(EmployeeMapper employeeMapper) {
//        this.employeeMapper = employeeMapper;
//    }


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
        return convertToDto(employeeService.getEmployeeById(empId).orElseThrow(() -> new ApiRequestException("User not found with empId:" + empId)));
    }

    @PostMapping
    public ResponseEntity<Integer> addEmployee(@NonNull @RequestBody Employee employee) {
        int added = employeeService.addEmployee(employee);
        return new ResponseEntity<>(added, HttpStatus.OK);
    }

    @PutMapping(path = "{empId}")
    public ResponseEntity<Integer> updateEmployee(@NonNull @PathVariable("empId") Integer empId, @RequestBody Employee employee) {
        int updated = employeeService.updateEmployee(empId, employee);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping(path = "{empId}")
    public int deleteEmployee(@PathVariable("empId") Integer empId) {
        return employeeService.deleteEmployee(empId);
    }

    @GetMapping(path = "{projectId}/employees")
    public List<Employee> getEmployeeByProjectId(@PathVariable("projectId") Integer projectId) {
//        return employeeService.getEmployeeByProjectId(projectId).orElseThrow(() -> new ApiRequestException("Employee not found with projectId:" + projectId));
        return employeeService.getEmployeeByProjectId(projectId);
    }

    private EmployeeDto convertToDto(Employee employee){
        return modelMapper.map(employee, EmployeeDto.class);
    }

    private Employee convertToEntity(EmployeeDto employeeDto){
        return modelMapper.map(employeeDto, Employee.class);
    }

}
