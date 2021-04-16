package com.example.RecruitEmployee.employee;

import com.example.RecruitEmployee.dto.EmployeeDto;
import com.example.RecruitEmployee.exception.ApiRequestException;
import com.example.RecruitEmployee.mapper.EmployeeMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeMapper employeeMapper;

    private final ModelMapper modelMapper;

    @Autowired
    public EmployeeService(@Qualifier("EmployeeMapper") EmployeeMapper employeeMapper, ModelMapper modelMapper) {
        this.employeeMapper = employeeMapper;
        this.modelMapper = modelMapper;
    }

    public List<Employee> getAllEmployee() {
        return employeeMapper.getAllEmployee();
    }

    public EmployeeDto getEmployeeById(Integer empId) {
//        return employeeMapper.getEmployeeById(empId);
        return convertToDto(employeeMapper.getEmployeeById(empId).orElseThrow(() -> new ApiRequestException("User not found with empId:" + empId)));
    }


    public int addEmployee(Employee employee) {
//        check adding same employee
        return employeeMapper.addEmployee(employee);
    }

    public int updateEmployee(Employee employee) {
        employeeMapper.getEmployeeById(employee.getEmpId()).orElseThrow(() -> new ApiRequestException("User not found with empId:" + employee.getEmpId()));
        return employeeMapper.updateEmployee(employee);
    }

    public int deleteEmployee(Integer empId) {
        employeeMapper.getEmployeeById(empId).orElseThrow(() -> new ApiRequestException("User not found with empId:" + empId));
        return employeeMapper.deleteEmployee(empId);
    }

    public List<Employee> getEmployeeByProjectId(Integer projectId) {
        List<Employee> employees = employeeMapper.getEmployeeByProjectId(projectId);
        if (employees.isEmpty()) throw new ApiRequestException("Employee not found with projectId:" + projectId);
        else return employees;
    }

    public PageInfo<Employee> getPaginatedEmployee(int pageNo, int pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        List<Employee> pagedResult = employeeMapper.getAllEmployee();
        return new PageInfo<Employee>(pagedResult);
    }

    private EmployeeDto convertToDto(Employee employee){
        return modelMapper.map(employee, EmployeeDto.class);
    }

    private Employee convertToEntity(EmployeeDto employeeDto){
        return modelMapper.map(employeeDto, Employee.class);
    }
}
