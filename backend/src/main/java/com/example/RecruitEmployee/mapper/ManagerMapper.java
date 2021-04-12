package com.example.RecruitEmployee.mapper;

import com.example.RecruitEmployee.employee.Employee;
import com.example.RecruitEmployee.manager.Manager;
import org.apache.ibatis.annotations.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

//    @Delete("DELETE Employee FROM Employee INNER JOIN Project ON Employee.project_id=Project.project_id INNER JOIN ProjectManager ON Project.manager_id=ProjectManager.manager_id WHERE ProjectManager.manager_id = #{managerId}")
//    int deleteEmployeeByManagerId(Integer managerId);

    @Select("select * from ProjectManager")
    List<Manager> getAllManager();

    @Select("select * from ProjectManager where manager_id = #{managerId}")
    Optional<Manager> getManagerById(Integer id);

    @Insert("INSERT INTO ProjectManager(manager_id, manager_name, manager_email, manager_contact_number) Values(#{managerId},#{managerName},#{managerEmail},#{managerContactNumber})")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "managerId", before = false, resultType = Integer.class)
    int addManager(Manager manager);

    @Update("UPDATE ProjectManager SET manager_name=#{managerName},manager_email=#{managerEmail},manager_contact_number=#{managerContactNumber} Where manager_id = #{managerId}")
    int updateManager(Manager manager);

    @Delete("DELETE FROM ProjectManager WHERE manager_id=#{managerId}")
    int deleteManager(Integer managerId);
}
