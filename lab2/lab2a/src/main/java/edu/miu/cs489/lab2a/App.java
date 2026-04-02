package edu.miu.cs489.lab2a;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import edu.miu.cs489.lab2a.data.DataStore;
import edu.miu.cs489.lab2a.model.Employee;
import edu.miu.cs489.lab2a.service.EmployeeService;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        List<Employee> employees = DataStore.loadEmployees();
        EmployeeService employeeService = new EmployeeService();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            printMenu();
            String choice = scanner.nextLine().trim();

            try {
                switch (choice) {
                    case "1" -> {
                        var allEmployees = employeeService.getAllEmployeesSorted(employees);
                        System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(allEmployees));
                    }
                    case "2" -> {
                        var report = employeeService.getCurrentQuarterEnrollees(employees, LocalDate.now());
                        System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(report));
                    }
                    case "3" -> {
                        var report = employeeService.getNextQuarterUpcomingEnrollees(employees, LocalDate.now());
                        System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(report));
                    }
                    case "0" -> running = false;
                    default -> System.out.println("Invalid option. Please choose 0, 1, 2, or 3.");
                }
            } catch (Exception e) {
                System.out.println("Error while processing request: " + e.getMessage());
            }

            if (running) {
                System.out.println();
            }
        }

        scanner.close();
        System.out.println("Application exited.");
    }

    private static void printMenu() {
        System.out.println("================ Employee Pension CLI ================");
        System.out.println("1. Print all employees in JSON (sorted)");
        System.out.println("2. Print Current Quarterly Enrollees report in JSON");
        System.out.println("3. Print Next Quarterly Upcoming Enrollees report in JSON");
        System.out.println("0. Exit");
        System.out.print("Select an option: ");
    }
}
