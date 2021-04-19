package com.example.RecruitEmployee.mapper;

import com.example.RecruitEmployee.employee.Employee;
import org.apache.ibatis.annotations.*;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;
import java.util.Optional;

@Mapper
@Qualifier("EmployeeMapper")
public interface EmployeeMapper {

    @Select("SELECT * FROM Employee")
    List<Employee> getAllEmployee();

    @Select("SELECT * FROM Employee WHERE emp_id = #{empId}")
    Optional<Employee> getEmployeeById(Integer empId);

    @Insert("INSERT INTO Employee(emp_id, emp_name, emp_salary, project_id, emp_email) VALUES(#{empId}, #{empName}, #{empSalary}, #{projectId}, #{empEmail})")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "empId", before = false, resultType = Integer.class)
    int addEmployee(Employee employee);

    @Update("UPDATE Employee SET emp_name=#{empName}, emp_salary=#{empSalary}, project_id=#{projectId}, emp_email=#{empEmail} WHERE emp_id=#{empId}")
    int updateEmployee(Employee employee);

    @Delete("DELETE FROM Employee WHERE emp_id=#{empId}")
    int deleteEmployee(Integer empId);

    @Select("SELECT * FROM Employee WHERE project_id=#{projectId}")
    List<Employee> getEmployeeByProjectId(Integer projectId);

    @Select("SELECT * FROM Employee WHERE emp_email = #{empEmail}")
    Optional<Employee> getEmployeeByEmail(String empEmail);
}
