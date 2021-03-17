package com.example.RecruitEmployee.mapper;

import com.example.RecruitEmployee.manager.Manager;
import com.example.RecruitEmployee.project.Project;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ManagerMapper {
    @Select("SELECT * FROM Project Manager")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "email", column = "email"),
            @Result(property = "contactNumber", column = "contact_number")})
    public List<Manager> findAllManagers();
}
