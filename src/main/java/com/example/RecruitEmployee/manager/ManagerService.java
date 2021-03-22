package com.example.RecruitEmployee.manager;

import com.example.RecruitEmployee.employee.Employee;
import com.example.RecruitEmployee.mapper.ManagerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagerService {
    private final ManagerMapper managerMapper;

    @Autowired
    public ManagerService(@Qualifier("ManagerMapper") ManagerMapper managerMapper) {
        this.managerMapper = managerMapper;
    }

    public List<Employee> getAllEmployee() {
        return managerMapper.getAllEmployee();
    }

    public List<Employee> getEmployeeByManagerId(Integer managerId) {
        return managerMapper.getEmployeeByManagerId(managerId);
    }


}
