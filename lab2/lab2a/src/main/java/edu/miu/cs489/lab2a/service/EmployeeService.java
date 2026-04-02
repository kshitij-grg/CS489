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

    public List<Employee> getCurrentQuarterEnrollees(List<Employee> employees, LocalDate today) {
        return getQuarterlyEnrollees(employees, today, 0, Comparator.comparing(Employee::getEmploymentDate).reversed());
    }

    public List<Employee> getNextQuarterUpcomingEnrollees(List<Employee> employees, LocalDate today) {
        return getQuarterlyEnrollees(
                employees,
                today,
                1,
                Comparator.comparing(Employee::getEmploymentDate).reversed()
                        .thenComparing(Employee::getYearlySalary)
        );
    }

    private List<Employee> getQuarterlyEnrollees(List<Employee> employees,
                                                 LocalDate today,
                                                 int quarterOffset,
                                                 Comparator<Employee> comparator) {
        LocalDate quarterStart = getQuarterStart(today, quarterOffset);
        LocalDate quarterEnd = quarterStart.plusMonths(3).minusDays(1);

        return employees.stream()
                .filter(employee -> !employee.hasPensionPlan())
                .filter(employee -> employee.getYearlySalary().compareTo(MIN_ELIGIBLE_SALARY) >= 0)
                .filter(employee -> {
                    LocalDate eligibilityDate = employee.getEmploymentDate().plusYears(1);
                    return !eligibilityDate.isBefore(quarterStart) && !eligibilityDate.isAfter(quarterEnd);
                })
                .sorted(comparator)
                .toList();
    }

    private LocalDate getQuarterStart(LocalDate date, int quarterOffset) {
        int quarterStartMonth = ((date.getMonthValue() - 1) / 3) * 3 + 1 + (quarterOffset * 3);
        int year = date.getYear() + (quarterStartMonth - 1) / 12;
        int month = ((quarterStartMonth - 1) % 12) + 1;

        return LocalDate.of(year, month, 1);
    }
}
