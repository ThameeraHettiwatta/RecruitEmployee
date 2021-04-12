package com.example.RecruitEmployee.manager;

import com.example.RecruitEmployee.employee.Employee;
import com.example.RecruitEmployee.exception.ApiRequestException;
import com.github.pagehelper.PageInfo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public List<Manager> getAllEmployees(){
        return managerService.getAllManager();
    }

//    @GetMapping(path = "{pageNo}/{pageSize}")
//    public PageInfo<Employee> getPaginatedEmployee(@PathVariable("pageNo") int pageNo, @PathVariable("pageSize") int pageSize) {
//        return managerService.getPaginatedEmployee(pageNo, pageSize);
//    }

    @GetMapping(path = "getEmployee/{managerId}")
    public Optional<Manager> getManagerById(@PathVariable("managerId") Integer managerId){
        return Optional.ofNullable(managerService.getManagerById(managerId).orElseThrow(() -> new ApiRequestException("User not found with managerId:" + managerId)));
    }

    @GetMapping(path = "getEmployees/{managerId}")
    public List<Employee> getEmployeeByManagerId(@PathVariable("managerId") Integer managerId){
        return managerService.getEmployeeByManagerId(managerId);
    }

    @PostMapping(path = "addEmployee")
    public int addManager(@NonNull @RequestBody Manager manager){
        return managerService.addManager(manager);
    }

    @PutMapping(path = "updateEmployee")
    public int updateManager(@NonNull @RequestBody Manager manager) {
        return managerService.updateManager(manager);
    }

    @DeleteMapping(path = "deleteEmployee/{managerId}")
    public int deleteManager(@PathVariable("managerId") Integer managerId) {
        return managerService.deleteManager(managerId);
    }


}
