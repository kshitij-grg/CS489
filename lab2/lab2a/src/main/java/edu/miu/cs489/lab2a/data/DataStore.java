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
                LocalDate.parse("2025-08-17"),
                new BigDecimal("105945.50"),
                null
        ));

        employees.add(new Employee(
                2L,
                "Benard",
                "Shaw",
                LocalDate.parse("2025-02-03"),
                new BigDecimal("197750.00"),
                new PensionPlan("EX0089", LocalDate.parse("2026-02-03"), new BigDecimal("450.00"))
        ));

        employees.add(new Employee(
                3L,
                "Carly",
                "Jones",
                LocalDate.parse("2024-05-16"),
                new BigDecimal("842000.75"),
                new PensionPlan("SM2307", LocalDate.parse("2025-05-17"), new BigDecimal("1555.50"))
        ));

        employees.add(new Employee(
                4L,
                "Wesley",
                "Schneider",
                LocalDate.parse("2025-04-30"),
                new BigDecimal("174500.00"),
                null
        ));

        employees.add(new Employee(
                5L,
                "Anna",
                "Wiltord",
                LocalDate.parse("2025-09-15"),
                new BigDecimal("185750.00"),
                null
        ));

        employees.add(new Employee(
                6L,
                "Yosef",
                "Tesfalem",
                LocalDate.parse("2025-07-31"),
                new BigDecimal("100000.00"),
                null
        ));

        employees.add(new Employee(
                7L,
                "Johnny",
                "Edwards",
                LocalDate.parse("2025-07-09"),
                new BigDecimal("95500.00"),
                null
        ));

        return employees;
    }
}
