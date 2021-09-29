package com.sac.EmployeeApp.service;

import com.sac.EmployeeApp.model.Department;
import com.sac.EmployeeApp.model.Employee;
import com.sac.EmployeeApp.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DisplayName("Employee Service")
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class EmployeeServiceTest {

    @MockBean
    private EmployeeRepository employeeRepository;

    @MockBean
    private DepartmentService departmentService;

    @Autowired
    private EmployeeService employeeService;

    private Employee nullEmployee;
    private Department actualDepartment;
    private Employee actualEmployee;

    @Nested
    @DisplayName("Find an employee by ID")
    class FindEmployeeById {

        @Test
        @DisplayName("should return null when no data available for given id")
        void should_return_null_when_no_data_available_for_given_id() {
            Mockito.doReturn(Optional.ofNullable(nullEmployee)).when(employeeRepository).findById(1L);

            Employee employee = employeeService.getEmployeeById(1);
            assertNull(employee, "Employee should be null");
        }

        @Test
        @DisplayName("should return employee when data available for given id")
        void should_return_employee_when_data_available_for_given_id() {
            Mockito.doReturn(Optional.of(actualEmployee)).when(employeeRepository).findById(1L);
            Mockito.doReturn(actualDepartment).when(departmentService).getDepartmentById(1L);

            Employee employee = employeeService.getEmployeeById(1);
            assertNotNull(employee, "Employee should not be null");
            assertEquals(employee.getFirstName(), actualEmployee.getFirstName(), "Employee available and expected data should match to actual data");
        }
    }

    @BeforeAll
    public void init() {
        // Init null employee
        nullEmployee = null;

        // Init a department
        actualDepartment = new Department();
        actualDepartment.setDepartmentID(1);
        actualDepartment.setDepartmentName("HR");

        // Init an employee
        actualEmployee = new Employee();
        actualEmployee.setEmployeeID(1);
        actualEmployee.setFirstName("Sachith");
        actualEmployee.setLastName("Harshamal");
        actualEmployee.setDepartment(actualDepartment);
    }
}