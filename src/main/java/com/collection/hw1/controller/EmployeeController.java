package com.collection.hw1.controller;

import com.collection.hw1.Employee;
import com.collection.hw1.service.EmployeeService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @ExceptionHandler({HttpStatusCodeException.class})
    public String handleException(HttpStatusCodeException e) {
        return "Code: " + e.getStatusCode() + ". Error: " + e.getMessage();
    }

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/add")
    public Employee add(@RequestParam String firstName, @RequestParam String lastName, @RequestParam double salary, @RequestParam Integer departmentId) {
        return employeeService.add(firstName, lastName, salary, departmentId);
    }

    @GetMapping("/find")
    public Employee find(@RequestParam String firstName, @RequestParam String lastName, @RequestParam double salary, @RequestParam Integer departmentId) {
        return employeeService.find(firstName, lastName, salary, departmentId);
    }

    @GetMapping("/remove")
    public Employee remove(@RequestParam String firstName, @RequestParam String lastName, @RequestParam double salary, @RequestParam Integer departmentId) {
        return employeeService.remove(firstName, lastName, salary, departmentId);
    }

    @GetMapping
    public List<Employee> getAll() {
        return employeeService.getAll();
    }
}
