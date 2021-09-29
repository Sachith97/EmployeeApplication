package com.sac.EmployeeApp.controller;

import com.sac.EmployeeApp.model.Employee;
import com.sac.EmployeeApp.service.EmployeeService;
import com.sac.EmployeeApp.vo.CommonResponseVO;
import com.sac.EmployeeApp.vo.EmployeeRequestVO;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/find-employee", produces = "application/json")
    public Employee getEmployee(@RequestParam("employeeID") long employeeID) {
        return employeeService.getEmployeeById(employeeID);
    }

    @GetMapping(path = "/find-employees", produces = "application/json")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @PostMapping(path = "/new-employee", produces = "application/json")
    public CommonResponseVO newEmployee(@RequestBody EmployeeRequestVO employeeRequestVO) {
        return employeeService.newEmployee(employeeRequestVO);
    }

    @PutMapping(path = "/update-employee", produces = "application/json")
    public CommonResponseVO updateEmployee(@RequestBody Employee employee) {
        return employeeService.updateEmployee(employee);
    }

    @DeleteMapping(path = "/delete-employee", produces = "application/json")
    public CommonResponseVO deleteEmployee(@RequestParam("employeeID") long employeeID) {
        return employeeService.deleteEmployee(employeeID);
    }
}
