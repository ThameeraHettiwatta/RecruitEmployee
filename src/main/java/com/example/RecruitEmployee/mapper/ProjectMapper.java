package com.example.RecruitEmployee.mapper;

import com.example.RecruitEmployee.project.Project;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProjectMapper {
    @Select("SELECT * FROM Project")
    @Results({
            @Result(id = true, property = "projectId", column = "project_id"),
            @Result(property = "projectName", column = "project_name"),
            @Result(property = "projectLocation", column = "project_location"),
            @Result(property = "manager", column = "manager_id", one = @One(select = "com.example.RecruitEmployee.mapper.ManagerMapper.findAllManagers"))
    })
    List<Project> findAllProjects();
}
