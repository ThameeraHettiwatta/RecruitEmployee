package com.example.RecruitEmployee.project;

import com.example.RecruitEmployee.mapper.ProjectMapper;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/project")
public class ProjectController {

    private final ProjectMapper projectMapper;

    public ProjectController(ProjectMapper projectMapper) {
        this.projectMapper = projectMapper;
    }

    @GetMapping("/getAllProject")
    public List<Project> getAllProject(){
        return projectMapper.getAllProject();
    }

    @GetMapping("/getProject/{projectId}")
    public Project getProjectById(@PathVariable("projectId") Integer projectId){
        return projectMapper.getProjectById(projectId);
    }

    @PostMapping("/addProject")
    public void addProject(@NonNull @RequestBody Project project){
        projectMapper.addProject(project);
    }
}
