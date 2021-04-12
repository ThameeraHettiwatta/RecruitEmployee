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
import java.util.Optional;

@Service
public class ManagerService {
    private final ManagerMapper managerMapper;
    private final EmployeeMapper employeeMapper;

    @Autowired
    public ManagerService(@Qualifier("ManagerMapper") ManagerMapper managerMapper, @Qualifier("EmployeeMapper") EmployeeMapper employeeMapper) {
        this.managerMapper = managerMapper;
        this.employeeMapper = employeeMapper;
    }

    public List<Manager> getAllManager() {
        return managerMapper.getAllManager();
    }

    public List<Employee> getEmployeeByManagerId(Integer managerId) {
        return managerMapper.getEmployeeByManagerId(managerId);
    }

    public Optional<Manager> getManagerById(Integer managerId) {
        return managerMapper.getManagerById(managerId);
    }

    public int addManager(Manager manager) {
        return managerMapper.addManager(manager);
    }

    public int updateManager(Manager manager) {
        return managerMapper.updateManager(manager);
    }

    public int deleteManager(Integer managerId) {
        return managerMapper.deleteManager(managerId);
    }

//    public PageInfo<Employee> getPaginatedEmployee(int pageNo, int pageSize) {
//        PageHelper.startPage(pageNo, pageSize);
//        List<Employee> pagedResult = employeeMapper.getAllEmployee();
//        return new PageInfo<Employee>(pagedResult);
//    }

}
