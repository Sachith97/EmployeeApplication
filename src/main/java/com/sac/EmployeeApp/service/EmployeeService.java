package com.sac.EmployeeApp.service;

import com.sac.EmployeeApp.model.Employee;
import com.sac.EmployeeApp.vo.CommonResponseVO;
import com.sac.EmployeeApp.vo.EmployeeRequestVO;

import java.util.List;

public interface EmployeeService {

    Employee getEmployeeById(long employeeID);

    List<Employee> getAllEmployees();

    CommonResponseVO newEmployee(EmployeeRequestVO employeeRequestVO);

    CommonResponseVO updateEmployee(Employee employee);

    CommonResponseVO deleteEmployee(long employeeID);
}
