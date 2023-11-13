package com.collection.hw1.service;

import com.collection.hw1.Employee;
import com.collection.hw1.exception.EmployeeStorageIsFullException;
import org.junit.jupiter.api.Test;

import static com.collection.hw1.service.utils.EmployeeGenerator.*;
import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceTest {
    private final EmployeeService employeeService = new EmployeeService();


    @Test
    void add_success() {
        String firstName = FIRST_NAME;
        String lastName = LAST_NAME;
        int salary = SALARY;
        int departmentId = FIRST_DEPARTMENT_ID;

        Employee expectedEmployee = getEmployee();

        Employee actualEmployee = employeeService.add (firstName,lastName, salary, departmentId);
        assertEquals(expectedEmployee, actualEmployee);
    }
    @Test
    void add_withEmployeeStorageIsFullException(){
        String firstName = FIRST_NAME;
        String lastName = LAST_NAME;
        int salary = SALARY;
        int departmentId = FIRST_DEPARTMENT_ID;

        String firstName2 = FIRST_NAME_2;
        String lastName2 = LAST_NAME_2;
       int salary2 = SALARY_2;

        String firstName3 = FIRST_NAME_3;
        String lastName3 = LAST_NAME_3;
        int salary3 = SALARY_3;

        String expectedMessege = "400 Массив сотрудников переполнен";

        employeeService.add (firstName3, lastName3, salary3, departmentId);
        employeeService.add (firstName2, lastName2, salary2, departmentId);
        Exception exception = assertThrows(
                EmployeeStorageIsFullException.class,
                () -> employeeService.add (firstName3, lastName3, salary3, departmentId));
                assertEquals(expectedMessege, exception.getMessage());
    }

    @Test
    void remove() {
    }

    @Test
    void get() {
    }

    @Test
    void getAll() {
    }
}