package com.example.RecruitEmployee.employee;

import com.example.RecruitEmployee.dto.EmployeeDto;
import com.example.RecruitEmployee.exception.ApiRequestException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.ArrayList;
import java.util.List;
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

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllEmployee() throws Exception{
        List<EmployeeDto> employees = new ArrayList<>();
        EmployeeDto employee1 = new EmployeeDto();
        employee1.setEmpId(1);
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
        EmployeeDto employee1 = new EmployeeDto();
        employee1.setEmpId(1);
        employee1.setEmpName("emp1");
        employee1.setEmpEmail("emp1@gmail.com");
        employee1.setEmpSalary(4852L);
        employee1.setProjectId(8);
        Mockito.when(employeeService.getEmployeeById(1)).thenReturn(employee1);
        mockMvc.perform(get("/employees/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.empId", Matchers.equalTo(1)));
    }

    @Test
    void addEmployee() throws Exception{
        Employee employee1 = new Employee(2, "emp1", 4852L, 8, "emp1@gmail.com");
        mockMvc.perform(post("/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employee1)))
                .andExpect(status().isCreated());
    }

    @Test
    void updateEmployee() throws Exception{
        Employee employee1 = new Employee(2, "emp1", 4852L, 8, "emp1@gmail.com");
        mockMvc.perform(put("/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employee1)))
                .andExpect(status().isOk());
    }

    @Test
    void deleteEmployee() throws Exception{
        Employee employee1 = new Employee(2, "emp1", 4852L, 8, "emp1@gmail.com");
        mockMvc.perform(delete("/employees/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employee1)))
                .andExpect(status().isOk());
    }

    @Test
    void getEmployeeByProjectId() throws Exception{
        List<EmployeeDto> employees = new ArrayList<>();
        EmployeeDto employee1 = new EmployeeDto();
        employee1.setEmpId(1);
        employee1.setEmpName("emp1");
        employee1.setEmpEmail("emp1@gmail.com");
        employee1.setEmpSalary(4852L);
        employee1.setProjectId(8);
        employees.add(employee1);
        Mockito.when(employeeService.getEmployeeByProjectId(8)).thenReturn(employees);
        mockMvc.perform(get("/employees/8/employees")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].empId", Matchers.equalTo(1)));
    }

    @Test
    void testGetEmployeeByIdWithInvalidKey() throws Exception{
        ApiRequestException apiRequestException = new ApiRequestException("User not found with empId:"+144);
        Mockito.when(employeeService.getEmployeeById(144)).thenThrow(apiRequestException);
        mockMvc.perform(get("/employees/144")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isBadRequest());
    }
}
