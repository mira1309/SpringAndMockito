package com.collection.hw1.service;


import com.collection.hw1.Employee;
import com.collection.hw1.exception.EmployeeAlreadyAddedException;
import com.collection.hw1.exception.EmployeeNotFoundedException;
import com.collection.hw1.exception.EmployeeStorageIsFullException;
import com.collection.hw1.exception.ValidationException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeService {
    private final Map<String, Employee> employees;

    public EmployeeService() {
        this.employees = new HashMap<>();
    }

    private final static int MAX_SIZE = 2;

    public Employee add(String firstName, String lastName, int salary, int departmentId) {

        firstName = StringUtils.capitalize(firstName);
        lastName = StringUtils.capitalize(lastName);

        validateFirstAndLastNames (firstName, lastName);

        if (employees.size() >= MAX_SIZE) {
            throw new  EmployeeStorageIsFullException("Массив сотрудников переполнен");
        }

        Employee newEmployee = new Employee(firstName, lastName, salary, departmentId);
        if (employees.containsKey( newEmployee.getFullName())) {
            throw new EmployeeAlreadyAddedException("Такой сотрудник уже есть");
        }
        employees.put(newEmployee.getFullName(), newEmployee);
        return newEmployee;
    }

    public Employee remove(String firstName, String lastName, int salary, int departmentId) {

        firstName = StringUtils.capitalize(firstName);
        lastName = StringUtils.capitalize(lastName);

        validateFirstAndLastNames (firstName, lastName);

        Employee employeeForRemove = new Employee(firstName, lastName, salary, departmentId);
        if (!employees.containsKey(employeeForRemove.getFullName())) {
            throw new EmployeeNotFoundedException("Невозможно удалить, сотрудника не существует");
        }
        employees.remove(employeeForRemove.getFullName());
        return employeeForRemove;
    }

    public Employee get(String firstName, String lastName, int salary, int departmentId) {

        firstName = StringUtils.capitalize(firstName);
        lastName = StringUtils.capitalize(lastName);

        validateFirstAndLastNames (firstName, lastName);

        Employee employeeForSearch = new Employee(firstName, lastName, salary, departmentId);
        if (!employees.containsKey(employeeForSearch.getFullName())) {
            throw new EmployeeNotFoundedException("Невозможно найти, сотрудника не существует");
        }
        return employees.get(employeeForSearch.getFullName());
    }
        public Collection<Employee> getAll() {
           return Collections.unmodifiableCollection(employees.values());
        }
        private void validateFirstAndLastNames(String firstName, String lastName){
        if(!StringUtils.isAlpha(firstName)){
            throw new ValidationException(" Имя содержит запрещенные символы");
        }
        }
    }

