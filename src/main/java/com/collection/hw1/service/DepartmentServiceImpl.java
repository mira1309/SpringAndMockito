package com.collection.hw1.service;

import com.collection.hw1.Employee;
import com.collection.hw1.exception.EmployeeNotFoundedException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService{
    private final EmployeeService employeeService;
    public DepartmentServiceImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @Override
    public Employee getEmployeeWithMaxSalary(Integer departmentId){
        return employeeService.getAll()
                .stream()
                .filter(e -> e.getDepartment() == departmentId)
                .max(Comparator.comparingInt(Employee::getSalary))
                .orElseThrow(() -> new EmployeeNotFoundedException("Employee not found"));
    }
    @Override
    public Employee getEmployeeWithMinSalary (Integer departmentId) {

        return employeeService.getAll()
                .stream()
                .filter(e -> e.getDepartment() == departmentId)
                .min(Comparator.comparingInt(Employee ::getSalary))
                .orElseThrow(() -> new EmployeeNotFoundedException("Employee not found"));
    }
    @Override
    public Collection<Employee> getEmployee(Integer departmentId) {
        return employeeService.getAll()
                .stream()
                .filter(e -> e.getDepartment() == departmentId)
                .collect(Collectors.toList());
    }



    @Override
    public Map<Integer, List<Employee>> getEmployee(){
        return employeeService.getAll()
                .stream()
                .collect(Collectors.groupingBy(Employee :: getDepartment));
    }
}










