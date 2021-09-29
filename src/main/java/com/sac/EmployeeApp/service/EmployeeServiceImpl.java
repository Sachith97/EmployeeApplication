package com.sac.EmployeeApp.service;

import com.sac.EmployeeApp.enums.Response;
import com.sac.EmployeeApp.model.Department;
import com.sac.EmployeeApp.model.Employee;
import com.sac.EmployeeApp.repository.EmployeeRepository;
import com.sac.EmployeeApp.vo.CommonResponseVO;
import com.sac.EmployeeApp.vo.EmployeeRequestVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger log = LoggerFactory.getLogger(EmployeeServiceImpl.class);
    private final EmployeeRepository employeeRepository;
    private final DepartmentService departmentService;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, DepartmentService departmentService) {
        this.employeeRepository = employeeRepository;
        this.departmentService = departmentService;
    }

    @Override
    public Employee getEmployeeById(long employeeID) {
        Optional<Employee> employee = employeeRepository.findById(employeeID);
        return employee.orElse(null);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public CommonResponseVO newEmployee(EmployeeRequestVO employeeRequestVO) {
        if(employeeRequestVO == null || employeeRequestVO.getFirstName().isEmpty() || employeeRequestVO.getLastName().isEmpty()) {
            return new CommonResponseVO(Response.INSUFFICIENT_DATA);
        }
        Department department = departmentService.getDepartmentById(employeeRequestVO.getDepartmentID());
        if(department == null) {
            return new CommonResponseVO(Response.UNAVAILABLE_DEPARTMENT);
        }
        Employee employee = new Employee();
        employee.setFirstName(employeeRequestVO.getFirstName());
        employee.setLastName(employeeRequestVO.getLastName());
        employee.setDepartment(department);
        try {
            employeeRepository.save(employee);
        } catch (Exception exception) {
            log.error("error occurred in inserting phase | " + exception);
            return new CommonResponseVO(Response.FAILED);
        }
        return new CommonResponseVO(Response.SUCCESS);
    }

    @Override
    public CommonResponseVO updateEmployee(Employee employeeRequest) {
        if(employeeRequest == null || employeeRequest.getFirstName().isEmpty() || employeeRequest.getLastName().isEmpty()) {
            return new CommonResponseVO(Response.INSUFFICIENT_DATA);
        }
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeRequest.getEmployeeID());
        if(!optionalEmployee.isPresent()) {
            return new CommonResponseVO(Response.UNAVAILABLE_EMPLOYEE);
        }
        Department department = departmentService.getDepartmentById(employeeRequest.getDepartment().getDepartmentID());
        if(department == null) {
            return new CommonResponseVO(Response.UNAVAILABLE_DEPARTMENT);
        }
        Employee employee = optionalEmployee.get();
        employee.setFirstName(employeeRequest.getFirstName());
        employee.setLastName(employeeRequest.getLastName());
        employee.setDepartment(department);
        try {
            employeeRepository.save(employee);
        } catch (Exception exception) {
            log.error("error occurred in updating phase | " + exception);
            return new CommonResponseVO(Response.FAILED);
        }
        return new CommonResponseVO(Response.SUCCESS);
    }

    @Override
    public CommonResponseVO deleteEmployee(long employeeID) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeID);
        if(!optionalEmployee.isPresent()) {
            return new CommonResponseVO(Response.UNAVAILABLE_EMPLOYEE);
        }
        try {
            employeeRepository.deleteById(employeeID);
        } catch (Exception exception) {
            log.error("error occurred in deleting phase | " + exception);
            return new CommonResponseVO(Response.FAILED);
        }
        return new CommonResponseVO(Response.SUCCESS);
    }
}
