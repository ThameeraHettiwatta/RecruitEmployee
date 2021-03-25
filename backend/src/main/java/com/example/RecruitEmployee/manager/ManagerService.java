package com.example.RecruitEmployee.manager;

import com.example.RecruitEmployee.employee.Employee;
import com.example.RecruitEmployee.mapper.EmployeeMapper;
import com.example.RecruitEmployee.mapper.ManagerMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagerService {
    private final ManagerMapper managerMapper;
    private final EmployeeMapper employeeMapper;

    @Autowired
    public ManagerService(@Qualifier("ManagerMapper") ManagerMapper managerMapper, @Qualifier("EmployeeMapper") EmployeeMapper employeeMapper) {
        this.managerMapper = managerMapper;
        this.employeeMapper = employeeMapper;
    }

    public List<Employee> getAllEmployee() {
        return employeeMapper.getAllEmployee();
    }

    public List<Employee> getEmployeeByManagerId(Integer managerId) {
        return managerMapper.getEmployeeByManagerId(managerId);
    }

    public int addEmployee(Employee employee) {
        return employeeMapper.addEmployee(employee);
    }

    public int updateEmployee(Integer empId, Employee employee) {
        return employeeMapper.updateEmployee(empId, employee);
    }

    public int deleteEmployeeByManagerId(Integer managerId) {
        return managerMapper.deleteEmployeeByManagerId(managerId);
    }

    public PageInfo<Employee> getPaginatedEmployee(int pageNo, int pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        List<Employee> pagedResult = employeeMapper.getAllEmployee();
        return new PageInfo<Employee>(pagedResult);
    }
}
