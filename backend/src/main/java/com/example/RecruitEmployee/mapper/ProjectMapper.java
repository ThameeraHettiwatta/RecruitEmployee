package com.example.RecruitEmployee.mapper;

import com.example.RecruitEmployee.project.Project;
import org.apache.ibatis.annotations.*;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;
import java.util.Optional;

@Mapper
@Qualifier("ProjectMapper")
public interface ProjectMapper {

    @Select("SELECT * FROM Project")
    List<Project> getAllProject();

    @Select("SELECT * FROM Project WHERE project_id=#{projectId}")
    Optional<Project> getProjectById(Integer projectId);

    @Insert("INSERT INTO Project(project_id, project_name, project_location, manager_id) VALUES(#{projectId}, #{projectName}, #{projectLocation}, #{managerId})")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "projectId", before = false, resultType = Integer.class)
    int addProject(Project project);

    @Update("UPDATE Project SET project_name=#{projectName}, project_location=#{projectLocation}, manager_id=#{managerId} WHERE project_id=#{projectId}")
    int updateProject(Project project);

    @Delete("DELETE FROM Project WHERE project_id=#{projectId}")
    int deleteProject(Integer projectId);

    @Select("SELECT * FROM Project WHERE manager_id=#{managerId}")
    List<Project> getProjectByManagerId(Integer managerId);

    @Select("SELECT * FROM Project WHERE project_name=#{projectName}")
    Optional<Project> getProjectByName(String projectName);
}
