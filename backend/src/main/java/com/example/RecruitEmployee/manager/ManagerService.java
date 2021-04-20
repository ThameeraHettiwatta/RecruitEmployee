package com.example.RecruitEmployee.manager;

import com.example.RecruitEmployee.dto.EmployeeDto;
import com.example.RecruitEmployee.dto.ManagerDto;
import com.example.RecruitEmployee.employee.Employee;
import com.example.RecruitEmployee.exception.ApiRequestException;
import com.example.RecruitEmployee.mapper.EmployeeMapper;
import com.example.RecruitEmployee.mapper.ManagerMapper;
import com.example.RecruitEmployee.mapper.ProjectMapper;
import com.example.RecruitEmployee.project.Project;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ManagerService {
    private final ManagerMapper managerMapper;
    private final EmployeeMapper employeeMapper;
    private final ProjectMapper projectMapper;
    private final ModelMapper modelMapper;

    @Autowired
    public ManagerService(@Qualifier("ManagerMapper") ManagerMapper managerMapper, @Qualifier("EmployeeMapper") EmployeeMapper employeeMapper, ProjectMapper projectMapper, ModelMapper modelMapper) {
        this.managerMapper = managerMapper;
        this.employeeMapper = employeeMapper;
        this.projectMapper = projectMapper;
        this.modelMapper = modelMapper;
    }

    public List<ManagerDto> getAllManager() {
        List<Manager> managers = managerMapper.getAllManager();
        return managers.stream().map((manager)->{
            ManagerDto managerDto;
            managerDto = convertToDto(manager);
            return managerDto;
        }).collect(Collectors.toList());
    }

    public List<EmployeeDto> getEmployeeByManagerId(Integer managerId) {
        List<Employee> employees = managerMapper.getEmployeeByManagerId(managerId);
        return employees.stream().map((employee)->{
            EmployeeDto employeeDto;
            employeeDto = convertToDto(employee);
            return employeeDto;
        }).collect(Collectors.toList());
    }

    public ManagerDto getManagerById(Integer managerId) {
        return convertToDto(managerMapper.getManagerById(managerId).orElseThrow(() -> new ApiRequestException("Manager not found with managerId:" + managerId)));
    }

    public int addManager(Manager manager) {
        Optional<Manager> managerByEmail = managerMapper.getManagerByEmail(manager.getManagerEmail());
        if (managerByEmail.isPresent()) throw new ApiRequestException("Manager already exist");
        return managerMapper.addManager(manager);
    }

    public int updateManager(Manager manager) {
        managerMapper.getManagerById(manager.getManagerId()).orElseThrow(() -> new ApiRequestException("Manager not found with managerId:" + manager.getManagerId()));
        return managerMapper.updateManager(manager);
    }

    public int deleteManager(Integer managerId) {
        managerMapper.getManagerById(managerId).orElseThrow(() -> new ApiRequestException("Manager not found with managerId:" +managerId));
        List<Project> projects = projectMapper.getProjectByManagerId(managerId);
        if(!projects.isEmpty()) throw new ApiRequestException("Cannot delete Manager, since its linked to Projects");
        return managerMapper.deleteManager(managerId);
    }

    private ManagerDto convertToDto(Manager manager){
        return modelMapper.map(manager, ManagerDto.class);
    }

    private EmployeeDto convertToDto(Employee employee){
        return modelMapper.map(employee, EmployeeDto.class);
    }
}
