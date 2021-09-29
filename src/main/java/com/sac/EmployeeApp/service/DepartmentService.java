package com.sac.EmployeeApp.service;

import com.sac.EmployeeApp.vo.CommonResponseVO;
import com.sac.EmployeeApp.vo.DepartmentRequestVO;
import com.sac.EmployeeApp.model.Department;

public interface DepartmentService {

    Department getDepartmentById(long departmentID);

    CommonResponseVO newDepartment(DepartmentRequestVO departmentRequestVO);
}
