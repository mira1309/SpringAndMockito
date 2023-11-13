package com.collection.hw1.service;

import com.collection.hw1.Employee;
import com.collection.hw1.exception.EmployeeNotFoundedException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;
import java.util.concurrent.Callable;

import static com.collection.hw1.service.utils.EmployeeGenerator.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class DepartmentServiceImplTest {
    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private DepartmentService departmentService;

    @Test
    void getEmployeeWithMaxSalary_success() {
        Integer departmentId = FIRST_DEPARTMENT_ID;

        when(employeeService.getAll()).thenReturn(getAllEmployee());

        Employee expectedEmployee = getEmployee();

        Employee actualEmployee = departmentService.getEmployeeWithMaxSalary(departmentId);
        assertEquals(expectedEmployee, actualEmployee);
        assertTrue(getEmployee().getSalary() > getEmployee2().getSalary());
    }

    @Test
    void getEmployeeWithMaxSalary_withEmployeeNotFoundedException() {
        Integer departmentId = FIRST_DEPARTMENT_ID;

        when(employeeService.getAll()).thenReturn(Collections.emptyList());

        String expectedMessage = "404 Сотрудник с максимальной зарплатой не найден";

        Exception exception = assertThrows(
                EmployeeNotFoundedException.class,
                () -> departmentService.getEmployeeWithMaxSalary(departmentId)
        );
        assertEquals(expectedMessage, exception.getMessage());
    }


    @Test
    void getEmployeeWithMinSalary_success() {
        Integer departmentId = FIRST_DEPARTMENT_ID;

        //Подготовка ожидаемого результата
        when(employeeService.getAll()).thenReturn(getAllEmployee());

        Employee expectedEmployee = getEmployee2();

        //Начало теста
        Employee actualEmployee = departmentService.getEmployeeWithMinSalary(departmentId);
        assertEquals(expectedEmployee, actualEmployee);
        assertTrue(getEmployee().getSalary() > getEmployee2().getSalary());
    }

    @Test
    void getEmployeeWithMinSalary_withEmployeeNotFoundedException() {
        Integer departmentId = FIRST_DEPARTMENT_ID;

        //Подготовка ожидаемого результата
        when(employeeService.getAll()).thenReturn(Collections.emptyList());

        String expectedMessage = "404 Сотрудник с минимальной зарплатой не найден";

        //Начало теста
        Exception exception = assertThrows(
                EmployeeNotFoundedException.class,
                () -> departmentService.getEmployeeWithMinSalary(departmentId)
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
        Collection<Employee> actualMap = departmentService.getEmployee(departmentId);
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

        Collection<Employee> actualMap = departmentService.getEmployee(departmentId);
        assertEquals(expectedMap, actualMap);
        assertEquals(getEmployee().getDepartmentId(), getEmployee2().getDepartmentId());
        assertNotEquals(getEmployee().getDepartmentId(), getEmployee3().getDepartmentId());
    }
}