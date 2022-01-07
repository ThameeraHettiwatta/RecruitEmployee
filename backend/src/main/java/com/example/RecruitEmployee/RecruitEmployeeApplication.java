package com.example.RecruitEmployee;

import com.example.RecruitEmployee.employee.Employee;
import org.apache.ibatis.type.MappedTypes;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
//@RestController
//@MappedTypes(Employee.class)
//@MapperScan("com.example.RecruitEmployee.mapper")
public class RecruitEmployeeApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecruitEmployeeApplication.class, args);
	}
//
//	@GetMapping
//	public String hello(){
//		return "Hello";
//	}
}
