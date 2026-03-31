package edu.miu.cs489.lab2a.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Employee {
    private long employeeId;
    private String firstName;
    private String lastName;
    private LocalDate employmentDate;
    private BigDecimal yearlySalary;
    private PensionPlan pensionPlan;

    public Employee(long employeeId,
                    String firstName,
                    String lastName,
                    LocalDate employmentDate,
                    BigDecimal yearlySalary,
                    PensionPlan pensionPlan) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.employmentDate = employmentDate;
        this.yearlySalary = yearlySalary;
        this.pensionPlan = pensionPlan;
    }

    public long getEmployeeId() {
        return employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getEmploymentDate() {
        return employmentDate;
    }

    public BigDecimal getYearlySalary() {
        return yearlySalary;
    }

    public PensionPlan getPensionPlan() {
        return pensionPlan;
    }

    public boolean hasPensionPlan() {
        return pensionPlan != null;
    }
}
