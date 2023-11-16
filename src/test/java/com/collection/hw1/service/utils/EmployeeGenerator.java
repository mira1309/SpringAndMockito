package com.collection.hw1.service.utils;

import com.collection.hw1.Employee;

import java.util.Arrays;
import java.util.List;

public class EmployeeGenerator {
    public static final String FIRST_NAME = "Ivan";
    public static final String LAST_NAME = "Ivanov";
    public static final int SALARY = 45000;

    public static final String FIRST_NAME_2 = "IvanSecond";
    public static final String LAST_NAME_2 = "IvanovSecond";
    public static final int SALARY_2 = 10000;

    public static final String FIRST_NAME_3 = "IvanThird";
    public static final String LAST_NAME_3 = "IvanovThird";
    public static final int SALARY_3 = 67000;

    public static final int FIRST_DEPARTMENT_ID = 1;

    public static final int SECOND_DEPARTMENT_ID = 2;

    public static Employee getEmployeeIsEmpty(){
        return null;
    }

    public static Employee getEmployee() {

        return new Employee(FIRST_NAME, LAST_NAME, SALARY, FIRST_DEPARTMENT_ID);
    }

    public static Employee getEmployee2() {
        return new Employee(FIRST_NAME_2, LAST_NAME_2, SALARY_2, FIRST_DEPARTMENT_ID);
    }

    public static Employee getEmployee3() {
        return new Employee(FIRST_NAME_3, LAST_NAME_3, SALARY_3, SECOND_DEPARTMENT_ID);
    }

    public static List<Employee> getAllEmployee() {
        return Arrays.asList(getEmployee(), getEmployee2(), getEmployee3());
    }
}
