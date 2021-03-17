package com.example.RecruitEmployee.mapper;

import com.example.RecruitEmployee.employee.Employee;
import com.example.RecruitEmployee.project.Project;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EmployeeMapper {

    @Select("SELECT * FROM Employee")
    @Results({
            @Result(id = true, property = "empId", column = "emp_id"),
            @Result(property = "empName", column = "emp_name"),
            @Result(property = "empSalary", column = "emp_salary"),
            @Result(property = "project", column = "project_id",
                    one = @One(select = "com.example.RecruitEmployee.mapper.ProjectMapper.findAllProjects")),
            @Result(property = "empEmail", column = "emp_email")
    })
    List<Employee> findAllEmployees();

    @Select("SELECT * FROM Employee WHERE emp_id = #{empId}")
    List<Employee> getEmployeeById(Integer empId);
}
