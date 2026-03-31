package edu.miu.cs489.lab2a.service;

import edu.miu.cs489.lab2a.model.Employee;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.Comparator;
import java.util.List;

public class EmployeeService {
    private static final BigDecimal MIN_ELIGIBLE_SALARY = new BigDecimal("100000.00");

    public List<Employee> getAllEmployeesSorted(List<Employee> employees) {
        return employees.stream()
                .sorted(Comparator
                        .comparing(Employee::getYearlySalary, Comparator.reverseOrder())
                        .thenComparing(Employee::getLastName))
                .toList();
    }

    public List<Employee> getQuarterlyUpcomingEnrollees(List<Employee> employees, LocalDate today) {
        LocalDate nextQuarterStart = getNextQuarterStart(today);
        LocalDate nextQuarterEnd = nextQuarterStart.plusMonths(3).minusDays(1);

        return employees.stream()
                .filter(employee -> !employee.hasPensionPlan())
                .filter(employee -> employee.getYearlySalary().compareTo(MIN_ELIGIBLE_SALARY) >= 0)
                .filter(employee -> {
                    LocalDate eligibilityDate = employee.getEmploymentDate().plusYears(1);
                    return !eligibilityDate.isBefore(nextQuarterStart) && !eligibilityDate.isAfter(nextQuarterEnd);
                })
                .sorted(Comparator.comparing(Employee::getEmploymentDate).reversed())
                .toList();
    }

    private LocalDate getNextQuarterStart(LocalDate date) {
        int month = date.getMonthValue();
        int currentQuarterStartMonth = ((month - 1) / 3) * 3 + 1;
        Month nextQuarterMonth = Month.of(currentQuarterStartMonth).plus(3);
        int year = date.getYear();

        if (nextQuarterMonth.getValue() <= currentQuarterStartMonth) {
            year += 1;
        }

        return LocalDate.of(year, nextQuarterMonth, 1);
    }
}
