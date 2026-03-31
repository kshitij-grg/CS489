package edu.miu.cs489.lab2a.data;

import edu.miu.cs489.lab2a.model.Employee;
import edu.miu.cs489.lab2a.model.PensionPlan;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DataStore {
    public static List<Employee> loadEmployees() {
        List<Employee> employees = new ArrayList<>();

        employees.add(new Employee(
                1L,
                "Daniel",
                "Agar",
                LocalDate.parse("2023-01-17"),
                new BigDecimal("105945.50"),
                new PensionPlan("EX1089", null, new BigDecimal("100.00"))
        ));

        employees.add(new Employee(
                2L,
                "Benard",
                "Shaw",
                LocalDate.parse("2022-09-03"),
                new BigDecimal("197750.00"),
                new PensionPlan(null, LocalDate.parse("2025-09-03"), null)
        ));

        employees.add(new Employee(
                3L,
                "Carly",
                "Agar",
                LocalDate.parse("2014-05-16"),
                new BigDecimal("842000.75"),
                new PensionPlan("SM2307", LocalDate.parse("2017-05-17"), new BigDecimal("1555.50"))
        ));

        employees.add(new Employee(
                4L,
                "Wesley",
                "Schneider",
                LocalDate.parse("2023-07-21"),
                new BigDecimal("74500.00"),
                null
        ));

        employees.add(new Employee(
                5L,
                "Anna",
                "Wiltord",
                LocalDate.parse("2023-03-15"),
                new BigDecimal("85750.00"),
                null
        ));

        employees.add(new Employee(
                6L,
                "Yosef",
                "Tesfalem",
                LocalDate.parse("2024-10-31"),
                new BigDecimal("100000.00"),
                null
        ));

        return employees;
    }
}
