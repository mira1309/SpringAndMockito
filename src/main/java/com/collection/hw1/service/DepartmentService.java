package com.collection.hw1.service;

import com.collection.hw1.Employee;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface DepartmentService {
    Employee getEmployeeWithMaxSalary (Integer departmentId);
    Employee getEmployeeWithMinSalary (Integer departmentId);
    Collection<Employee> getEmployee (Integer departmentId);
    Map<Integer, List<Employee>> getEmployee();

}
