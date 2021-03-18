package com.example.RecruitEmployee.mapper;

import com.example.RecruitEmployee.employee.Employee;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EmployeeMapper {

    @Select("SELECT * FROM Employee")
//    @Results({
//            @Result(id = true, property = "empId", column = "emp_id"),
//            @Result(property = "empName", column = "emp_name"),
//            @Result(property = "empSalary", column = "emp_salary"),
//            @Result(property = "project", column = "project_id",
//                    one = @One(select = "com.example.RecruitEmployee.mapper.ProjectMapper.findAllProjects")),
//            @Result(property = "empEmail", column = "emp_email")
//    })
    List<Employee> getAllEmployee();

    @Select("SELECT * FROM Employee WHERE emp_id = #{empId}")
    Employee getEmployeeById(Integer empId);

    @Insert("INSERT INTO Employee(emp_id, emp_name, emp_salary, project_id, emp_email) VALUES(#{empId}, #{empName}, #{empSalary}, #{projectId}, #{empEmail})")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "empId", before = false, resultType = Integer.class)
    void addEmployee(Employee employee);

    @Update("UPDATE Employee SET emp_name=#{empName}, emp_salary=#{empSalary}, project_id=#{projectId}, emp_email=#{empEmail} WHERE emp_id=#{empId}")
    void updateEmployee(Integer empId, Employee employee);

    @Delete("DELETE FROM Employee WHERE emp_id=#{empId}")
    void deleteEmployee(Integer empId);

    @Select("SELECT * FROM Employee WHERE project_id=#{projectId}")
    List<Employee> getEmployeeByProjectId(Integer projectId);
}
