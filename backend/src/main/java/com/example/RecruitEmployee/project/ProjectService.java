package com.example.RecruitEmployee.project;

import com.example.RecruitEmployee.mapper.ProjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {
    private final ProjectMapper projectMapper;

    @Autowired
    public ProjectService(@Qualifier("ProjectMapper") ProjectMapper projectMapper) {
        this.projectMapper = projectMapper;
    }

    public List<Project> getAllProject() {
        return projectMapper.getAllProject();
    }

    public PageInfo<Project> getPaginatedProject(int pageNo, int pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        List<Project> pagedResult = projectMapper.getAllProject();
        return new PageInfo<Project>(pagedResult);
    }

    public Optional<Project> getProjectById(Integer projectId) {
        return projectMapper.getProjectById(projectId);
    }

    public int addProject(Project project) {
        return projectMapper.addProject(project);
    }

    public int updateProject(Integer projectId, Project project) {
        return projectMapper.updateProject(projectId, project);
    }

    public int deleteProject(Integer projectId) {
        return projectMapper.deleteProject(projectId);
    }

    public List<Project> getProjectByManagerId(Integer managerId) {
        return projectMapper.getProjectByManagerId(managerId);
    }
}
