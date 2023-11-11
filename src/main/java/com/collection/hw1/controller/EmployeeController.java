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
        return "Code: " + e.getStatusCode() + ". Error: " +e.getMessage();
    }

    @ExceptionHandler(RuntimeException.class)
    public String handleExceptoin(RuntimeException e) {
        return e.getMessage();
    }
    private final EmployeeService employeeService;
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @GetMapping("/add")
    public Employee add(@RequestParam String firstName, @RequestParam String lastName) {
        return employeeService.add(firstName,lastName);
    }
    @GetMapping("/remove")
    public Employee remove(@RequestParam String firstName, @RequestParam String lastName) {
        return employeeService.remove(firstName,lastName);
    }
    @GetMapping("/get")
    public Employee get(@RequestParam String firstName, @RequestParam String lastName) {
        return employeeService.get(firstName,lastName);
    }
    @GetMapping
    public Collection<Employee> getAll(){
        return employeeService.getAll();
    }
}
