package com.collection.hw1.service;


import com.collection.hw1.Employee;
import com.collection.hw1.exception.EmployeeAlreadyAddedException;
import com.collection.hw1.exception.EmployeeNotFoundedException;
import com.collection.hw1.exception.EmployeeStorageIsFullException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Service
public class EmployeeService {
    private static final int MAX_SIZE = 2;
    private final List<Employee> employees = new ArrayList<>();

    public Employee add(String firstName, String lastName) {

        if (employees.size() > MAX_SIZE) {
            throw new EmployeeStorageIsFullException("Массив сотрудников переполнен");
        }

        Employee newEmployee = new Employee(firstName, lastName);
        if (employees.contains(newEmployee)) {
            throw new EmployeeAlreadyAddedException("Такой сотрудник уже есть");
        }

        employees.add(newEmployee);
        return newEmployee;
    }

    public Employee remove(String firstName, String lastName) {
        Employee employeeForRemove = new Employee(firstName, lastName);


        if (!employees.contains(employeeForRemove)) {
            throw new EmployeeNotFoundedException("Невозможно удалить, сотрудника не существует");
        }
        employees.remove(employeeForRemove);
        return employeeForRemove;
    }

    public Employee get(String firstName, String lastName) {
        Employee employeeForSearch = new Employee(firstName, lastName);

        if (!employees.contains(employeeForSearch)) {
            throw new EmployeeNotFoundedException("Невозможно найти, сотрудника не существует");
        }

            Employee result = null;
            for (Employee employee : employees) {
                if (employee.equals(employeeForSearch)) {
                    return employee;
                }
            }
            return result;
    }
        public List<Employee> getAll() {
           return employees;
        }
    }

