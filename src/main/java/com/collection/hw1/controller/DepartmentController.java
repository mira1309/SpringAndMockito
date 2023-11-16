package com.collection.hw1.controller;

import com.collection.hw1.Employee;
import com.collection.hw1.service.DepartmentService;
import com.collection.hw1.service.DepartmentServiceImpl;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    @ExceptionHandler({HttpStatusCodeException.class})
    public String handleException(HttpStatusCodeException e) {
        return "Code: " + e.getStatusCode() + ". Error: " + e.getMessage();
    }

    private final DepartmentServiceImpl departmentServiceImpl;

    public DepartmentController(DepartmentServiceImpl departmentServiceImpl) {
        this.departmentServiceImpl = departmentServiceImpl;
    }

    @GetMapping("/max-salary")
    public Employee getEmployeeWithMaxSalary(@RequestParam Integer departmentId) {
        return departmentServiceImpl.getEmployeeWithMaxSalary(departmentId);
    }

    @GetMapping("/min-salary")
    public Employee getEmployeeWithMinSalary(@RequestParam Integer departmentId) {
        return departmentServiceImpl.getEmployeeWithMinSalary(departmentId);
    }

    @GetMapping("/all")
    public Map<Integer, List<Employee>> getEmployeesByDepartment(@RequestParam(required = false) Integer departmentId) {
        return departmentServiceImpl.getEmployeesByDepartment(departmentId);
    }
}