package com.example.RecruitEmployee.manager;

import com.example.RecruitEmployee.employee.Employee;
import com.github.pagehelper.PageInfo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/managers")
public class ManagerController {

    private final ManagerService managerService;

    private final ModelMapper modelMapper;

    @Autowired
    public ManagerController(ManagerService managerService, ModelMapper modelMapper) {
        this.managerService = managerService;
        this.modelMapper = modelMapper;
    }

    @GetMapping(path = "getAllEmployee")
    public List<Employee> getAllEmployees(){
        return managerService.getAllEmployee();
    }

    @GetMapping(path = "{pageNo}/{pageSize}")
    public PageInfo<Employee> getPaginatedEmployee(@PathVariable("pageNo") int pageNo, @PathVariable("pageSize") int pageSize) {
        return managerService.getPaginatedEmployee(pageNo, pageSize);
    }


    @GetMapping(path = "getEmployees/{managerId}")
    public List<Employee> getEmployeeByManagerId(@PathVariable("managerId") Integer managerId){
        return managerService.getEmployeeByManagerId(managerId);
    }

    @PostMapping(path = "addEmployee")
    public int addEmployee(@NonNull @RequestBody Employee employee){
        return managerService.addEmployee(employee);
    }

    @PutMapping(path = "updateEmployee/{empId}")
    public int updateEmployee(@NonNull @PathVariable("empId") Integer empId, @RequestBody Employee employee) {
        return managerService.updateEmployee(empId, employee);
    }

    @DeleteMapping(path = "deleteEmployee/{managerId}")
    public int deleteEmployeeByManagerId(@PathVariable("managerId") Integer managerId) {
        return managerService.deleteEmployeeByManagerId(managerId);
    }


}
