package com.collection.hw1.service;

import com.collection.hw1.Employee;
import com.collection.hw1.exception.EmployeeNotFoundException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static com.collection.hw1.service.utils.EmployeeGenerator.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class DepartmentServiceImplTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private DepartmentServiceImpl departmentServiceImpl;

    @Test
    void getEmployeeWithMaxSalary_success() {
        //Подготовка входных данных
        Integer departmentId = FIRST_DEPARTMENT_ID;

        //Подготовка ожидаемого результата
        when(employeeService.getAll()).thenReturn(getAllEmployee());

        Employee expectedEmployee = getEmployee();

        //Начало теста
        Employee actualEmployee = departmentServiceImpl.getEmployeeWithMaxSalary(departmentId);
        assertEquals(expectedEmployee, actualEmployee);
        assertTrue(getEmployee().getSalary() > getEmployee2().getSalary());
    }

    @Test
    void getEmployeeWithMaxSalary_withEmployeeNotFoundException() {
        //Подготовка входных данных
        Integer departmentId = FIRST_DEPARTMENT_ID;

        //Подготовка ожидаемого результата
        when(employeeService.getAll()).thenReturn(Collections.emptyList());

        String expectedMessage = "404 Сотрудник с максимальной зарплатой не найден";

        //Начало теста
        Exception exception = assertThrows(
                EmployeeNotFoundException.class,
                () -> departmentServiceImpl.getEmployeeWithMaxSalary(departmentId)
        );
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void getEmployeeWithMinSalary_success() {
        //Подготовка входных данных
        Integer departmentId = FIRST_DEPARTMENT_ID;

        //Подготовка ожидаемого результата
        when(employeeService.getAll()).thenReturn(getAllEmployee());

        Employee expectedEmployee = getEmployee2();

        //Начало теста
        Employee actualEmployee = departmentServiceImpl.getEmployeeWithMinSalary(departmentId);
        assertEquals(expectedEmployee, actualEmployee);
        assertTrue(getEmployee().getSalary() > getEmployee2().getSalary());
    }

    @Test
    void getEmployeeWithMinSalary_withEmployeeNotFoundException() {
        //Подготовка входных данных
        Integer departmentId = FIRST_DEPARTMENT_ID;

        //Подготовка ожидаемого результата
        when(employeeService.getAll()).thenReturn(Collections.emptyList());

        String expectedMessage = "404 Сотрудник с минимальной зарплатой не найден";

        //Начало теста
        Exception exception = assertThrows(
                EmployeeNotFoundException.class,
                () -> departmentServiceImpl.getEmployeeWithMinSalary(departmentId)
        );
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void getEmployeesByDepartment_withDepartmentId() {
        //Подготовка входных данных
        Integer departmentId = FIRST_DEPARTMENT_ID;

        //Подготовка ожидаемого результата
        when(employeeService.getAll()).thenReturn(getAllEmployee());

        Map<Integer, List<Employee>> expectedMap = new HashMap<>();
        expectedMap.put(FIRST_DEPARTMENT_ID, Arrays.asList(getEmployee(), getEmployee2()));

        //Начало теста
        Map<Integer, List<Employee>> actualMap = departmentServiceImpl.getEmployeesByDepartment(departmentId);
        assertEquals(expectedMap, actualMap);
        assertEquals(getEmployee().getDepartmentId(), getEmployee2().getDepartmentId());
        assertNotEquals(getEmployee().getDepartmentId(), getEmployee3().getDepartmentId());
    }

    @Test
    void getEmployeesByDepartment_withoutDepartmentId() {
        //Подготовка входных данных
        Integer departmentId = null;

        //Подготовка ожидаемого результата
        when(employeeService.getAll()).thenReturn(getAllEmployee());

        Map<Integer, List<Employee>> expectedMap = new HashMap<>();
        expectedMap.put(FIRST_DEPARTMENT_ID, Arrays.asList(getEmployee(), getEmployee2()));
        expectedMap.put(SECOND_DEPARTMENT_ID, Collections.singletonList(getEmployee3()));

        //Начало теста
        Map<Integer, List<Employee>> actualMap = departmentServiceImpl.getEmployeesByDepartment(departmentId);
        assertEquals(expectedMap, actualMap);
        assertEquals(getEmployee().getDepartmentId(), getEmployee2().getDepartmentId());
        assertNotEquals(getEmployee().getDepartmentId(), getEmployee3().getDepartmentId());
    }
}