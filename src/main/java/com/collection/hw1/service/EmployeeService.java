package com.collection.hw1.service;


import com.collection.hw1.Employee;
import com.collection.hw1.exception.EmployeeAlreadyAddedException;
import com.collection.hw1.exception.EmployeeNotFoundedException;
import com.collection.hw1.exception.EmployeeStorageIsFullException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeService {
    private final Map<String, Employee> employees;

    public EmployeeService() {
        this.employees = new HashMap<>();
        add("Ivan", "Kurochkin");
        add("Mitya", "Petrov");
        add("Vova", "Putin");
        add("Sasha", "Ivanov");
    }

    public Employee add(String firstName, String lastName) {
        Employee newEmployee = new Employee(firstName, lastName);
        if (employees.containsKey( newEmployee.getFullName())) {
            throw new EmployeeAlreadyAddedException("Такой сотрудник уже есть");
        }
        employees.put(newEmployee.getFullName(), newEmployee);
        return newEmployee;
    }

    public Employee remove(String firstName, String lastName) {
        Employee employeeForRemove = new Employee(firstName, lastName);
        if (!employees.containsKey(employeeForRemove.getFullName())) {
            throw new EmployeeNotFoundedException("Невозможно удалить, сотрудника не существует");
        }
        employees.remove(employeeForRemove.getFullName());
        return employeeForRemove;
    }

    public Employee get(String firstName, String lastName) {
        Employee employeeForSearch = new Employee(firstName, lastName);
        if (!employees.containsKey(employeeForSearch.getFullName())) {
            throw new EmployeeNotFoundedException("Невозможно найти, сотрудника не существует");
        }
        return employees.get(employeeForSearch.getFullName());
    }
        public Collection<Employee> getAll() {
           return Collections.unmodifiableCollection(employees.values());
        }
    }

