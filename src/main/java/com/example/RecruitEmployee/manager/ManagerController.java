package com.example.RecruitEmployee.manager;

import com.example.RecruitEmployee.employee.Employee;
import com.example.RecruitEmployee.mapper.ManagerMapper;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/managers")
public class ManagerController {

    private final ManagerService managerService;

    private final ModelMapper modelMapper;

    public ManagerController(ManagerService managerService, ModelMapper modelMapper) {
        this.managerService = managerService;
        this.modelMapper = modelMapper;
    }

    @GetMapping(path = "getAllEmployee")
    public List<Employee> getAllEmployees(){
        return managerService.getAllEmployee();
    }

    @GetMapping(path = "getEmployees/{managerId}")
    public List<Employee> getEmployeeByManagerId(@PathVariable("managerId") Integer managerId){
        return managerService.getEmployeeByManagerId(managerId);
    }

}
