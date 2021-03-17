package com.example.RecruitEmployee.manager;

import com.example.RecruitEmployee.mapper.ManagerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/manager")
public class ManagerController {

    private final ManagerMapper managerMapper;

    public ManagerController(ManagerMapper managerMapper) {
        this.managerMapper = managerMapper;
    }

    @GetMapping("/getAllEmployee")
    public List<Manager> getAllEmployees(){
        return managerMapper.getAllEmployee();
    }


}
