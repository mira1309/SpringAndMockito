package com.collection.hw1.service;

import com.collection.hw1.Employee;

import com.collection.hw1.exception.EmployeeAlreadyAddedException;
import com.collection.hw1.exception.EmployeeStorageIsFullException;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

import static com.collection.hw1.service.utils.EmployeeGenerator.*;
import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceTest {

    private final EmployeeService employeeService = new EmployeeService();

    @Test
    void add_success() {
        //Подготовка входных данных
        String firstName = FIRST_NAME;
        String lastName = LAST_NAME;
        double salary = SALARY;
        int departmentId = FIRST_DEPARTMENT_ID;

        //Подготовка ожидаемого результата
        Employee expectedEmployee = getEmployee();

        //Начало теста
        Employee actualEmployee = employeeService.add(firstName, lastName, salary, departmentId);

        assertEquals(expectedEmployee, actualEmployee);
    }

    @Test
    void add_withEmployeeStorageIsFullException() {
        //Подготовка входных данных
        String firstName = FIRST_NAME;
        String lastName = LAST_NAME;
        double salary = SALARY;
        int departmentId = FIRST_DEPARTMENT_ID;

        String firstName2 = FIRST_NAME_2;
        String lastName2 = LAST_NAME_2;
        double salary2 = SALARY_2;

        String firstName3 = FIRST_NAME_3;
        String lastName3 = LAST_NAME_3;
        double salary3 = SALARY_3;

        //Подготовка ожидаемого результата
        String expectedMessage = "400 Массив сотрудников переполнен";

        //Начало теста
        employeeService.add(firstName2, lastName2, salary2, departmentId);
        employeeService.add(firstName3, lastName3, salary3, departmentId);
        Exception exception = assertThrows(
                EmployeeStorageIsFullException.class,
                () -> employeeService.add(firstName, lastName, salary, departmentId)
        );
        assertEquals(expectedMessage, exception.getMessage());
    }
    @Test
    void add_withEmployeeAlreadyAdded() {
        //Подготовка входных данных


        String firstName2 = FIRST_NAME_2;
        String lastName2 = LAST_NAME_2;
        double salary2 = SALARY_2;
        int departmentId = FIRST_DEPARTMENT_ID;

        String expectedMessage = "400 Такой сотрудник уже есть";

        employeeService.add(firstName2, lastName2, salary2, departmentId);

        Exception exception = assertThrows(
                EmployeeAlreadyAddedException.class,
                () -> employeeService.add(firstName2, lastName2, salary2, departmentId)
        );
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void find() {

    }

    @Test
    void remove_success() {
         int departmentId = FIRST_DEPARTMENT_ID;

        String firstName2 = FIRST_NAME_2;
        String lastName2 = LAST_NAME_2;
        double salary2 = SALARY_2;

        Employee expectedEmployee = getEmployeeIsEmpty();

        Employee actualEmployee = employeeService.remove(firstName2, lastName2, salary2, departmentId);
        assertEquals(expectedEmployee, actualEmployee);

    }

    @Test
    void getAll_succes() {
        String firstName = FIRST_NAME;
        String lastName = LAST_NAME;
        double salary = SALARY;
        int departmentId = FIRST_DEPARTMENT_ID;

        String firstName2 = FIRST_NAME_2;
        String lastName2 = LAST_NAME_2;
        double salary2 = SALARY_2;

        String firstName3 = FIRST_NAME_3;
        String lastName3 = LAST_NAME_3;
        double salary3 = SALARY_3;

        List<Employee> expectedEmployee = getAllEmployee();

        List<Employee> actualEmployee = new ArrayList<>();
        actualEmployee.add(getEmployee());
        actualEmployee.add(getEmployee2());
        actualEmployee.add(getEmployee3());



        assertEquals(expectedEmployee, actualEmployee);



    }
}