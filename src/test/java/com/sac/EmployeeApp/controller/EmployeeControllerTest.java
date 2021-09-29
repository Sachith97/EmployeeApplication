package com.sac.EmployeeApp.controller;

import com.sac.EmployeeApp.model.Department;
import com.sac.EmployeeApp.model.Employee;
import com.sac.EmployeeApp.service.EmployeeService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("Employee Controller")
class EmployeeControllerTest {

    private static final String BASE_URL = "/api/v1/employee";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private EmployeeService employeeService;

    private Employee actualEmployee;

    @Test
    @DisplayName("should return employee data when found")
    void should_return_employee_when_found_data() throws Exception {
        Mockito.doReturn(actualEmployee).when(employeeService).getEmployeeById(1);

        mvc.perform(get(BASE_URL + "/find-employee")
                .param("employeeID", "1").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.firstName", is(actualEmployee.getFirstName())));
    }

    @BeforeAll
    public void init() {
        // Create a department
        Department department = new Department();
        department.setDepartmentID(1);
        department.setDepartmentName("HR");

        // Init an employee
        actualEmployee = new Employee();
        actualEmployee.setEmployeeID(1);
        actualEmployee.setFirstName("Sachith");
        actualEmployee.setLastName("Harshamal");
        actualEmployee.setDepartment(department);
    }
}