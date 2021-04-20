package com.example.RecruitEmployee.project;

import com.example.RecruitEmployee.dto.ProjectDto;
import com.example.RecruitEmployee.employee.Employee;
import com.example.RecruitEmployee.exception.ApiRequestException;
import com.example.RecruitEmployee.salary.SalaryService;
import com.github.pagehelper.PageInfo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;
    private final SalaryService salaryService;

    @Autowired
    public ProjectController(ProjectService projectService, SalaryService salaryService, ModelMapper modelMapper) {
        this.projectService = projectService;
        this.salaryService = salaryService;
    }

    @GetMapping
    public ResponseEntity<List<ProjectDto>> getAllProject(){
        List<ProjectDto> projects = projectService.getAllProject();
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    @GetMapping(path = "{pageNo}/{pageSize}")
    public ResponseEntity<PageInfo<ProjectDto>> getPaginatedProject(@PathVariable("pageNo") int pageNo, @PathVariable("pageSize") int pageSize){
        PageInfo<ProjectDto> pageProject = projectService.getPaginatedProject(pageNo, pageSize);
        return new ResponseEntity<>(pageProject, HttpStatus.OK);
    }

    @GetMapping(path = "{projectId}")
    public ResponseEntity<ProjectDto> getProjectById(@PathVariable("projectId") Integer projectId){
        ProjectDto projectDto = projectService.getProjectById(projectId);
        return new ResponseEntity<>(projectDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Integer> addProject(@NonNull @RequestBody Project project){
        int added = projectService.addProject(project);
        return new ResponseEntity<>(added, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Integer> updateProject(@NonNull @RequestBody Project project){
        int updated = projectService.updateProject(project);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping(path = "{projectId}")
    public ResponseEntity<Integer> deleteProject(@PathVariable("projectId") Integer projectId){
        int deleted = projectService.deleteProject(projectId);
        return new ResponseEntity<>(deleted, HttpStatus.OK);
    }

    @GetMapping(path = "{managerId}/projects")
    public ResponseEntity<List<ProjectDto>> getProjectByManagerId(@PathVariable("managerId") Integer managerId){
        List<ProjectDto> projects = projectService.getProjectByManagerId(managerId);
        return  new ResponseEntity<>(projects, HttpStatus.OK);
    }

    @GetMapping(path = "/projectCost/{projectId}")
    public ResponseEntity<Long> getProjectCostByProjectId(@PathVariable("projectId") Integer projectId){
        long cost = salaryService.getProjectCost(projectId);
        return new ResponseEntity<>(cost, HttpStatus.OK);
    }

}
