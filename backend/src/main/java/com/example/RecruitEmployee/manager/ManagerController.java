package com.example.RecruitEmployee.manager;

import com.example.RecruitEmployee.dto.EmployeeDto;
import com.example.RecruitEmployee.dto.ManagerDto;
import com.example.RecruitEmployee.employee.Employee;
import com.example.RecruitEmployee.exception.ApiRequestException;
import com.github.pagehelper.PageInfo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/managers")
public class ManagerController {

    private final ManagerService managerService;

    @Autowired
    public ManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }

    @GetMapping(path = "getAllEmployee")
    public ResponseEntity<List<ManagerDto>> getAllEmployees(){
        List<ManagerDto> managerDtos = managerService.getAllManager();
        return new ResponseEntity<>(managerDtos, HttpStatus.OK);
    }

    @GetMapping(path = "getEmployee/{managerId}")
    public ResponseEntity<ManagerDto> getManagerById(@PathVariable("managerId") Integer managerId){
        ManagerDto managerDto = managerService.getManagerById(managerId);
        return new ResponseEntity<>(managerDto, HttpStatus.OK);
    }

    @GetMapping(path = "getEmployees/{managerId}")
    public ResponseEntity<List<EmployeeDto>> getEmployeeByManagerId(@PathVariable("managerId") Integer managerId){
        List<EmployeeDto> employeeDto= managerService.getEmployeeByManagerId(managerId);
        return new ResponseEntity<>(employeeDto, HttpStatus.OK);
    }

    @PostMapping(path = "addEmployee")
    public ResponseEntity<Integer> addManager(@NonNull @RequestBody Manager manager){
        int added = managerService.addManager(manager);
        return new ResponseEntity<>(added, HttpStatus.CREATED);
    }

    @PutMapping(path = "updateEmployee")
    public ResponseEntity<Integer> updateManager(@NonNull @RequestBody Manager manager) {
        int updated = managerService.updateManager(manager);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping(path = "deleteEmployee/{managerId}")
    public ResponseEntity<Integer> deleteManager(@PathVariable("managerId") Integer managerId) {
        int deleted = managerService.deleteManager(managerId);
        return new ResponseEntity<>(deleted, HttpStatus.OK);
    }


}
