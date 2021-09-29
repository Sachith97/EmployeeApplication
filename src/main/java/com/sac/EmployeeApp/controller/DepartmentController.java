package com.sac.EmployeeApp.controller;

import com.sac.EmployeeApp.vo.CommonResponseVO;
import com.sac.EmployeeApp.vo.DepartmentRequestVO;
import com.sac.EmployeeApp.service.DepartmentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping(path = "/new-department", produces = "application/json")
    public CommonResponseVO newDepartment(@RequestBody DepartmentRequestVO departmentRequestVO) {
        return departmentService.newDepartment(departmentRequestVO);
    }
}
