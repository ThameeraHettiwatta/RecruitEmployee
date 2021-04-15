package com.example.RecruitEmployee.employee;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @MockBean
    private ModelMapper modelMapper;

//    @InjectMocks
//    private EmployeeController employeeController;
//
//    @Before
//    public void setUp() throws Exception{
//        mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
//    }

    @Test
    void getAllEmployee() throws Exception{
        List<Employee> employees = new ArrayList<>();
        Employee employee1 = new Employee();
//        employee1.setEmpId(2);
        employee1.setEmpName("emp1");
        employee1.setEmpEmail("emp1@gmail.com");
        employee1.setEmpSalary(4852L);
        employee1.setProjectId(8);
        employees.add(employee1);
        Mockito.when(employeeService.getAllEmployee()).thenReturn(employees);
        mockMvc.perform(get("/employees")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].empName", Matchers.equalTo("emp1")));

    }

    @Test
    void getEmployeeById() throws Exception{
//        Optional<Employee> employee = Optional.ofNullable(null);
        Employee employee1 = new Employee();
        employee1.setEmpId(1);
        employee1.setEmpName("emp1");
        employee1.setEmpEmail("emp1@gmail.com");
        employee1.setEmpSalary(4852L);
        employee1.setProjectId(8);
//        employee.add(employee1);
        Mockito.when(employeeService.getEmployeeById(1)).thenReturn(Optional.of(employee1));
        mockMvc.perform(get("/employees/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.empName", Matchers.equalTo("emp1")));
    }

    @Test
    void addEmployee() {
    }

    @Test
    void updateEmployee() {
    }

    @Test
    void deleteEmployee() {
    }

    @Test
    void getEmployeeByProjectId() {
    }
}
