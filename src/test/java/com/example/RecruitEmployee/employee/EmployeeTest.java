package com.example.RecruitEmployee.employee;

import com.example.RecruitEmployee.project.Project;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {
    private Project project = new Project();
    private Employee emp = new Employee(1,"Emp1",  1000L,project,"emp1@gmail.com");

    @Test
    void setEmpName() {
        emp.setEmpName("Emp2");
        assertEquals("Emp2", emp.getEmpName());
    }

    @Test
    void setEmpId() {
        emp.setEmpId(2);
        assertEquals(2, emp.getEmpId());
    }

    @Test
    void setEmpSalary() {
        emp.setEmpSalary(2000L);
        assertEquals(2000L, emp.getEmpSalary());
    }

    @Test
    void setEmpEmail() {
        emp.setEmpEmail("emp2@gmail.com");
        assertEquals("emp2@gmail.com", emp.getEmpEmail());
    }

    @Test
    void getEmpName() {
        assertEquals("Emp1", emp.getEmpName());
    }

    @Test
    void getEmpId() {
        assertEquals(1, emp.getEmpId());
    }

    @Test
    void getEmpSalary() {
        assertEquals(1000L, emp.getEmpSalary());
    }

    @Test
    void getEmpEmail() {
        assertEquals("emp1@gmail.com", emp.getEmpEmail());
    }
}