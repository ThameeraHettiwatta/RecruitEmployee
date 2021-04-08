package com.example.RecruitEmployee.project;

import com.example.RecruitEmployee.dto.ProjectDto;
import com.example.RecruitEmployee.exception.ApiRequestException;
import com.example.RecruitEmployee.salary.SalaryService;
import com.github.pagehelper.PageInfo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;
    private final SalaryService salaryService;

    private final ModelMapper modelMapper;

    @Autowired
    public ProjectController(ProjectService projectService, SalaryService salaryService, ModelMapper modelMapper) {
        this.projectService = projectService;
        this.salaryService = salaryService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public List<Project> getAllProject(){
        return projectService.getAllProject();
    }

    @GetMapping(path = "{pageNo}/{pageSize}")
    public PageInfo<Project> getPaginatedProject(@PathVariable("pageNo") int pageNo, @PathVariable("pageSize") int pageSize){
        return projectService.getPaginatedProject(pageNo, pageSize);
    }

    @GetMapping(path = "{projectId}")
    public ProjectDto getProjectById(@PathVariable("projectId") Integer projectId){
        return convertToDto(projectService.getProjectById(projectId).orElseThrow(() -> new ApiRequestException("Project not found with projectId:" + projectId)));
    }

    @PostMapping
    public int addProject(@NonNull @RequestBody Project project){
        return projectService.addProject(project);
    }

    @PutMapping
    public int updateProject(@NonNull @RequestBody Project project){
        return projectService.updateProject(project);
    }

    @DeleteMapping(path = "{projectId}")
    public int deleteProject(@PathVariable("projectId") Integer projectId){
        return projectService.deleteProject(projectId);
    }

    @GetMapping(path = "{managerId}/projects")
    public List<Project> getProjectByManagerId(@PathVariable("managerId") Integer managerId){
        List<Project> projects = projectService.getProjectByManagerId(managerId);
        if (projects.isEmpty()) throw new ApiRequestException("Project not found with managerId:" + managerId);
        else return  projects;
    }

    @GetMapping(path = "/projectCost/{projectId}")
    public long getProjectCostByProjectId(@PathVariable("projectId") Integer projectId){
        return salaryService.getProjectCost(projectId);
    }

    private ProjectDto convertToDto(Project project){
       return modelMapper.map(project, ProjectDto.class);
    }

    private Project covertToEntity(ProjectDto projectDto){
        return modelMapper.map(projectDto, Project.class);
    }
}
