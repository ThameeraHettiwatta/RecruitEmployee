package com.example.RecruitEmployee.employee;

import com.example.RecruitEmployee.mapper.EmployeeMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

    public List<Employee> getAllEmployee() {
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

    public PageInfo<Employee> getPaginatedEmployee(int pageNo, int pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        List<Employee> pagedResult = employeeMapper.getPaginatedEmployee();
        return new PageInfo<Employee>(pagedResult);
    }
}
