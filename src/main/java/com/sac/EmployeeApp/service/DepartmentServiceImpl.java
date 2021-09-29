package com.sac.EmployeeApp.service;

import com.google.common.base.Optional;
import com.sac.EmployeeApp.model.Employee;
import com.sac.EmployeeApp.vo.CommonResponseVO;
import com.sac.EmployeeApp.vo.DepartmentRequestVO;
import com.sac.EmployeeApp.enums.Response;
import com.sac.EmployeeApp.model.Department;
import com.sac.EmployeeApp.repository.DepartmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private static final Logger log = LoggerFactory.getLogger(DepartmentServiceImpl.class);
    private final DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Department getDepartmentById(long departmentID) {
        Optional<Department> department = findById(departmentID);
        if(department.isPresent()) {
            return department.get();
        }
        return null;
    }

    @Override
    public CommonResponseVO newDepartment(DepartmentRequestVO departmentRequestVO) {
        if(departmentRequestVO == null || departmentRequestVO.getDepartmentName().isEmpty()) {
            return new CommonResponseVO(Response.INSUFFICIENT_DATA);
        }
        Department department = new Department();
        department.setDepartmentName(departmentRequestVO.getDepartmentName());
        try {
            departmentRepository.save(department);
        } catch (Exception exception) {
            log.error("error occurred in inserting phase | " + exception);
            return new CommonResponseVO(Response.FAILED);
        }
        return new CommonResponseVO(Response.SUCCESS);
    }

    private Optional<Department> findById(long departmentID) {
        List<Department> departments = departmentRepository.findAll();
        Optional<Employee> employeeOptional;
        for(Department department : departments) {
            if(department.getDepartmentID() == departmentID) {
                return Optional.of(department);
            }
        }
        return Optional.absent();
    }
}
