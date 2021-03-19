package com.example.RecruitEmployee.employee;

import com.example.RecruitEmployee.dto.EmployeeDto;
import com.example.RecruitEmployee.exception.ApiRequestException;
import com.example.RecruitEmployee.mapper.EmployeeMapper;
import com.example.RecruitEmployee.employee.EmployeeService;
import com.github.pagehelper.PageInfo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

//    private final EmployeeMapper employeeMapper;
//
//    public EmployeeController(EmployeeMapper employeeMapper) {
//        this.employeeMapper = employeeMapper;
//    }


    @GetMapping
    public List<Employee> getAllEmployee() {
        return employeeService.getAllEmployee();
    }

    @GetMapping(path = "{pageNo}/{pageSize}")
    public PageInfo<Employee> getPaginatedEmployee(@PathVariable("pageNo") int pageNo, @PathVariable("pageSize") int pageSize) {
        return employeeService.getPaginatedEmployee(pageNo, pageSize);
    }

    @GetMapping(path = "{empId}")
    public EmployeeDto getEmployeeById(@PathVariable("empId") Integer empId) {
        return convertToDto(employeeService.getEmployeeById(empId).orElseThrow(() -> new ApiRequestException("User not found with empId:" + empId)));
    }

    @PostMapping
    public int addEmployee(@NonNull @RequestBody Employee employee) {
        return employeeService.addEmployee(employee);
    }

    @PutMapping(path = "{empId}")
    public int updateEmployee(@NonNull @PathVariable("empId") Integer empId, @RequestBody Employee employee) {
        return employeeService.updateEmployee(empId, employee);
    }

    @DeleteMapping(path = "{empId}")
    public int deleteEmployee(@PathVariable("empId") Integer empId) {
        return employeeService.deleteEmployee(empId);
    }

    @GetMapping(path = "{projectId}/employees")
    public List<Employee> getEmployeeByProjectId(@PathVariable("projectId") Integer projectId) {
        return employeeService.getEmployeeByProjectId(projectId);
    }

    private EmployeeDto convertToDto(Employee employee){
        EmployeeDto employeeDto = modelMapper.map(employee, EmployeeDto.class);
        return employeeDto;
    }

    private Employee convertToEntity(EmployeeDto employeeDto){
        Employee employee = modelMapper.map(employeeDto, Employee.class);
        return employee;
    }

}
