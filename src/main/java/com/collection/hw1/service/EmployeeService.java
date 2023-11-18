package com.collection.hw1.service;


import com.collection.hw1.Employee;
import com.collection.hw1.exception.EmployeeAlreadyAddedException;
import com.collection.hw1.exception.EmployeeNotFoundException;

import com.collection.hw1.exception.EmployeeStorageIsFullException;
import com.collection.hw1.exception.ValidationException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeService {
    private final List<Employee> employees = new ArrayList<>();
    private final static int MAX_SIZE = 2;

    public Employee add(String firstName, String lastName, double salary, int departmentId) {

        firstName = StringUtils.capitalize(firstName);
        lastName = StringUtils.capitalize(lastName);



        validateFirstAndLastNames(firstName, lastName);

        if (employees.size() >= MAX_SIZE) {
            throw new EmployeeStorageIsFullException("Массив сотрудников переполнен");
        }
        Employee newEmployee = new Employee(firstName, lastName, salary, departmentId);
        if (employees.contains(newEmployee)) {
            throw new EmployeeAlreadyAddedException("Такой сотрудник уже есть");
        }
        employees.add(newEmployee);
        return newEmployee;
    }


    public Employee find(String firstName, String lastName, double salary, int departmentId) {
        firstName = StringUtils.capitalize(firstName);
        lastName = StringUtils.capitalize(lastName);

        validateFirstAndLastNames(firstName, lastName);

        Employee employeeForFound = new Employee(firstName, lastName, salary, departmentId);
        for (Employee e : employees) {
            if (e.equals(employeeForFound)) {
                return e;
            }
        }
        throw new EmployeeNotFoundException("Такого сотрудника нет");
    }

    public Employee remove(String firstName, String lastName, double salary, int departmentId) {
        firstName = StringUtils.capitalize(firstName);
        lastName = StringUtils.capitalize(lastName);

        validateFirstAndLastNames(firstName, lastName);

        Employee employeeForRemove = new Employee(firstName, lastName, salary, departmentId);

        boolean removeResult = employees.remove(employeeForRemove);
        if (removeResult) {
            return employeeForRemove;
        } else {
            throw new EmployeeNotFoundException("Сотрудник не удален - не был найден в базе");
        }
    }
    public List<Employee> getAll() {
        return employees;
    }

    private void validateFirstAndLastNames(String firstName, String lastName) {
        if (!StringUtils.isAlpha(firstName)) {
            throw new ValidationException("Имя содержит запрещенные символы");
        }

        if (!StringUtils.isAlpha(lastName)) {
            throw new ValidationException("Фамилия содержит запрещенные символы");
        }
    }
}

