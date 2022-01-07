package com.example.RecruitEmployee.project;

import com.example.RecruitEmployee.dto.ProjectDto;
import com.example.RecruitEmployee.employee.Employee;
import com.example.RecruitEmployee.exception.ApiRequestException;
import com.example.RecruitEmployee.mapper.EmployeeMapper;
import com.example.RecruitEmployee.mapper.ManagerMapper;
import com.example.RecruitEmployee.mapper.ProjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProjectService {
    private final ProjectMapper projectMapper;
    private final ModelMapper modelMapper;
    private final ManagerMapper managerMapper;
    private final EmployeeMapper employeeMapper;

    @Autowired
    public ProjectService(@Qualifier("ProjectMapper") ProjectMapper projectMapper, ModelMapper modelMapper, ManagerMapper managerMapper, EmployeeMapper employeeMapper) {
        this.projectMapper = projectMapper;
        this.modelMapper = modelMapper;
        this.managerMapper = managerMapper;
        this.employeeMapper = employeeMapper;
    }

    public List<ProjectDto> getAllProject() {
        List<Project> projects = projectMapper.getAllProject();
        return projects.stream().map((project)->{
            ProjectDto projectDto;
            projectDto = convertToDto(project);
            return projectDto;
        }).collect(Collectors.toList());
    }

    public PageInfo<ProjectDto> getPaginatedProject(int pageNo, int pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        List<Project> pagedResult = projectMapper.getAllProject();
        return new PageInfo<ProjectDto>(pagedResult.stream().map((project)->{
            ProjectDto projectDto;
            projectDto = convertToDto(project);
            return projectDto;
        }).collect(Collectors.toList()));
    }

    public ProjectDto getProjectById(Integer projectId) {
        return convertToDto(projectMapper.getProjectById(projectId).orElseThrow(() -> new ApiRequestException("Project not found with projectId:" + projectId)));
    }

    public int addProject(Project project) {
        Optional<Project> project1 = projectMapper.getProjectByName(project.getProjectName());
        managerMapper.getManagerById(project.getManagerId()).orElseThrow(()->new ApiRequestException("No Manager with Manager Id:" + project.getManagerId()));
        if(project1.isPresent()) throw new ApiRequestException("Project already exist");
        return projectMapper.addProject(project);
    }

    public int updateProject(Project project) {
        projectMapper.getProjectById(project.getProjectId()).orElseThrow(() -> new ApiRequestException("Project not found with projectId:" + project.getProjectId()));
        managerMapper.getManagerById(project.getManagerId()).orElseThrow(()->new ApiRequestException("No Manager with Manager Id:" + project.getManagerId()));
        return projectMapper.updateProject(project);
    }

    public int deleteProject(Integer projectId) {
        projectMapper.getProjectById(projectId).orElseThrow(() -> new ApiRequestException("Project not found with projectId:" + projectId));
        List<Employee> employees =employeeMapper.getEmployeeByProjectId(projectId);
        if(!employees.isEmpty()) throw new ApiRequestException("Cannot delete Project, since it's linked to Employees");
        return projectMapper.deleteProject(projectId);
    }

    public List<ProjectDto> getProjectByManagerId(Integer managerId) {
        List<Project> projects = projectMapper.getProjectByManagerId(managerId);
        if (projects.isEmpty()) throw new ApiRequestException("Project not found with managerId:" + managerId);
        return projects.stream().map((project)->{
            ProjectDto projectDto;
            projectDto = convertToDto(project);
            return projectDto;
        }).collect(Collectors.toList());
    }

    private ProjectDto convertToDto(Project project){
        return modelMapper.map(project, ProjectDto.class);
    }
}
