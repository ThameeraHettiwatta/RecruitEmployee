package com.example.RecruitEmployee.employee;

import com.example.RecruitEmployee.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeMapper employeeMapper;

    @Autowired
    public EmployeeService(@Qualifier("EmployeeMapper") EmployeeMapper employeeMapper) {
        this.employeeMapper = employeeMapper;
    }

    public List<Employee> getAllEmployee(){
        return employeeMapper.getAllEmployee();
    }

    public Optional<Employee> getEmployeeById(Integer empId) {
        return employeeMapper.getEmployeeById(empId);
    }

    public int addEmployee(Employee employee) {
        return employeeMapper.addEmployee(employee);
    }

    public int updateEmployee(Integer empId, Employee employee) {
        return employeeMapper.updateEmployee(empId, employee);
    }

    public int deleteEmployee(Integer empId) {
        return employeeMapper.deleteEmployee(empId);
    }

    public List<Employee> getEmployeeByProjectId(Integer projectId) {
        return employeeMapper.getEmployeeByProjectId(projectId);
    }

    public List<Employee> getPaginatedEmployee(int pageNo, int pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<Employee> pagedResult = employeeMapper.findAll(paging);
        return pagedResult.toList();
    }
}
