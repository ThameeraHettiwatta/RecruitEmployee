package com.example.RecruitEmployee.employee;

import com.example.RecruitEmployee.dto.EmployeeDto;
import com.example.RecruitEmployee.exception.ApiRequestException;
import com.example.RecruitEmployee.mapper.EmployeeMapper;
import com.example.RecruitEmployee.mapper.ProjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeMapper employeeMapper;

    private final ModelMapper modelMapper;

    private final ProjectMapper projectMapper;


    @Autowired
    public EmployeeService(@Qualifier("EmployeeMapper") EmployeeMapper employeeMapper, ModelMapper modelMapper, ProjectMapper projectMapper) {
        this.employeeMapper = employeeMapper;
        this.modelMapper = modelMapper;
        this.projectMapper = projectMapper;
    }

    public List<EmployeeDto> getAllEmployee() {
        List<Employee> employees = employeeMapper.getAllEmployee();
        return employees.stream().map((employee)->{
            EmployeeDto employeeDto;
            employeeDto = convertToDto(employee);
            return employeeDto;
        }).collect(Collectors.toList());
    }

    public EmployeeDto getEmployeeById(Integer empId) {
        return convertToDto(employeeMapper.getEmployeeById(empId).orElseThrow(() -> new ApiRequestException("User not found with empId:" + empId)));
    }


    public int addEmployee(Employee employee) {
        Optional<Employee> employeeByEmail = employeeMapper.getEmployeeByEmail(employee.getEmpEmail());
        if (employeeByEmail.isPresent()) throw new ApiRequestException("Employee already exist");
        projectMapper.getProjectById(employee.getProjectId()).orElseThrow(()->new ApiRequestException("No Project with Project Id:" + employee.getProjectId()));
        return employeeMapper.addEmployee(employee);
    }


    public int updateEmployee(Employee employee) {
        projectMapper.getProjectById(employee.getProjectId()).orElseThrow(()->new ApiRequestException("No Project with Project Id:" + employee.getProjectId()));
        employeeMapper.getEmployeeById(employee.getEmpId()).orElseThrow(() -> new ApiRequestException("User not found with empId:" + employee.getEmpId()));
        return employeeMapper.updateEmployee(employee);
    }

    public int deleteEmployee(Integer empId) {
        employeeMapper.getEmployeeById(empId).orElseThrow(() -> new ApiRequestException("User not found with empId:" + empId));
        return employeeMapper.deleteEmployee(empId);
    }

    public List<EmployeeDto> getEmployeeByProjectId(Integer projectId) {
        List<Employee> employees = employeeMapper.getEmployeeByProjectId(projectId);
        if (employees.isEmpty()) throw new ApiRequestException("Employee not found with projectId:" + projectId);
        return employees.stream().map((employee)->{
            EmployeeDto employeeDto;
            employeeDto = convertToDto(employee);
            return employeeDto;
        }).collect(Collectors.toList());
    }

    public PageInfo<EmployeeDto> getPaginatedEmployee(int pageNo, int pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        List<Employee> pagedResult = employeeMapper.getAllEmployee();
        return new PageInfo<EmployeeDto>(pagedResult.stream().map((employee)->{
            EmployeeDto employeeDto;
            employeeDto = convertToDto(employee);
            return employeeDto;
        }).collect(Collectors.toList()));
    }

    private EmployeeDto convertToDto(Employee employee){
        return modelMapper.map(employee, EmployeeDto.class);
    }
}
