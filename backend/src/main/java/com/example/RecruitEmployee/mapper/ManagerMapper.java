package com.example.RecruitEmployee.mapper;

import com.example.RecruitEmployee.employee.Employee;
import com.example.RecruitEmployee.manager.Manager;
import org.apache.ibatis.annotations.*;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper
@Qualifier("ManagerMapper")
public interface ManagerMapper {
//    @Select("SELECT * FROM Employee")
////    @Results({
////            @Result(property = "id", column = "id"),
////            @Result(property = "name", column = "name"),
////            @Result(property = "email", column = "email"),
////            @Result(property = "contactNumber", column = "contact_number")})
//    public List<Employee> getAllEmployee();

    @Select("SELECT emp_id, emp_name, emp_salary, Employee.project_id, emp_email FROM Employee INNER JOIN Project ON Employee.project_id=Project.project_id INNER JOIN ProjectManager ON Project.manager_id=ProjectManager.manager_id WHERE ProjectManager.manager_id=#{managerId}")
    List<Employee> getEmployeeByManagerId(Integer managerId);

    @Delete("DELETE Employee FROM Employee INNER JOIN Project ON Employee.project_id=Project.project_id INNER JOIN ProjectManager ON Project.manager_id=ProjectManager.manager_id WHERE ProjectManager.manager_id = #{managerId}")
    int deleteEmployeeByManagerId(Integer managerId);
}
