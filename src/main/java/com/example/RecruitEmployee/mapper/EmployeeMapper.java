package com.example.RecruitEmployee.mapper;

import com.example.RecruitEmployee.employee.Employee;
import org.apache.ibatis.annotations.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Mapper
@Qualifier("EmployeeMapper")
public interface EmployeeMapper extends PagingAndSortingRepository<Employee, Integer> {

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
    Optional<Employee> getEmployeeById(Integer empId);

    @Insert("INSERT INTO Employee(emp_id, emp_name, emp_salary, project_id, emp_email) VALUES(#{empId}, #{empName}, #{empSalary}, #{projectId}, #{empEmail})")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "empId", before = false, resultType = Integer.class)
    int addEmployee(Employee employee);

    @Update("UPDATE Employee SET emp_name=#{employee.empName}, emp_salary=#{employee.empSalary}, project_id=#{employee.projectId}, emp_email=#{employee.empEmail} WHERE emp_id=#{empId}")
    int updateEmployee(Integer empId, Employee employee);

    @Delete("DELETE FROM Employee WHERE emp_id=#{empId}")
    int deleteEmployee(Integer empId);

    @Select("SELECT * FROM Employee WHERE project_id=#{projectId}")
    List<Employee> getEmployeeByProjectId(Integer projectId);

    @Select("SELECT * FROM Employee")
    Page<Employee> getPaginatedEmployee(Pageable paging);
}
