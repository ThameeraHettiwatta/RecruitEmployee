package com.example.RecruitEmployee.project;

import com.example.RecruitEmployee.dto.ProjectDto;
import com.example.RecruitEmployee.exception.ApiRequestException;
import com.example.RecruitEmployee.mapper.ProjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {
    private final ProjectMapper projectMapper;
    private final ModelMapper modelMapper;

    @Autowired
    public ProjectService(@Qualifier("ProjectMapper") ProjectMapper projectMapper, ModelMapper modelMapper) {
        this.projectMapper = projectMapper;
        this.modelMapper = modelMapper;
    }

    public List<Project> getAllProject() {
        return projectMapper.getAllProject();
    }

    public PageInfo<Project> getPaginatedProject(int pageNo, int pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        List<Project> pagedResult = projectMapper.getAllProject();
        return new PageInfo<Project>(pagedResult);
    }

    public ProjectDto getProjectById(Integer projectId) {
        return convertToDto(projectMapper.getProjectById(projectId).orElseThrow(() -> new ApiRequestException("Project not found with projectId:" + projectId)));
    }

    public int addProject(Project project) {
        return projectMapper.addProject(project);
    }

    public int updateProject(Project project) {
        projectMapper.getProjectById(project.getProjectId()).orElseThrow(() -> new ApiRequestException("Project not found with projectId:" + project.getProjectId()));
        return projectMapper.updateProject(project);
    }

    public int deleteProject(Integer projectId) {
        projectMapper.getProjectById(projectId).orElseThrow(() -> new ApiRequestException("Project not found with projectId:" + projectId));
        return projectMapper.deleteProject(projectId);
    }

    public List<Project> getProjectByManagerId(Integer managerId) {
        List<Project> projects = projectMapper.getProjectByManagerId(managerId);
        if (projects.isEmpty()) throw new ApiRequestException("Project not found with managerId:" + managerId);
        else return projects;
    }

    private ProjectDto convertToDto(Project project){
        return modelMapper.map(project, ProjectDto.class);
    }

    private Project covertToEntity(ProjectDto projectDto){
        return modelMapper.map(projectDto, Project.class);
    }

}
