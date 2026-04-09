package edu.miu.cs.cs489.lab6.adsappointment;

import edu.miu.cs.cs489.lab6.adsappointment.model.*;
import edu.miu.cs.cs489.lab6.adsappointment.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@SpringBootApplication
public class ADSAppointmentCliApp {

    private final PatientService patientService;
    private final DentistService dentistService;
    private final SurgeryService surgeryService;
    private final AppointmentService appointmentService;

    public ADSAppointmentCliApp(PatientService patientService,
                               DentistService dentistService,
                               SurgeryService surgeryService,
                               AppointmentService appointmentService) {
        this.patientService = patientService;
        this.dentistService = dentistService;
        this.surgeryService = surgeryService;
        this.appointmentService = appointmentService;
    }

    public static void main(String[] args) {
        SpringApplication.run(ADSAppointmentCliApp.class, args);
    }

    @Bean
    public CommandLineRunner run() {
        return args -> {
            System.out.println("\n========== ADS Dental Surgeries Appointment Management System ==========\n");

            // Demonstrate CRUD Operations
            demonstrateCRUDOperations();

            System.out.println("\n========== Application Completed Successfully ==========\n");
        };
    }

    private void demonstrateCRUDOperations() {
        System.out.println("\n--- 1. READ ALL PATIENTS ---");
        printAllPatients();

        System.out.println("\n--- 2. READ ALL DENTISTS ---");
        printAllDentists();

        System.out.println("\n--- 3. READ ALL SURGERIES ---");
        printAllSurgeries();

        System.out.println("\n--- 4. READ ALL APPOINTMENTS ---");
        printAllAppointments();

        System.out.println("\n--- 5. READ PATIENT BY ID ---");
        Optional<Patient> patient = patientService.getPatientById(1);
        if (patient.isPresent()) {
            System.out.println("Patient found: " + patient.get());
        }

        System.out.println("\n--- 6. READ DENTIST BY ID ---");
        Optional<Dentist> dentist = dentistService.getDentistById(1);
        if (dentist.isPresent()) {
            System.out.println("Dentist found: " + dentist.get());
        }

        System.out.println("\n--- 7. READ APPOINTMENTS BY PATIENT NO (P100) ---");
        List<Appointment> patientAppointments = appointmentService.getAppointmentsByPatientNo("P100");
        System.out.println("Appointments for Patient P100: " + patientAppointments.size());
        patientAppointments.forEach(System.out::println);

        System.out.println("\n--- 8. READ APPOINTMENTS BY DENTIST NO (D001) ---");
        List<Appointment> dentistAppointments = appointmentService.getAppointmentsByDentistNo("D001");
        System.out.println("Appointments for Dentist D001: " + dentistAppointments.size());
        dentistAppointments.forEach(System.out::println);
    }

    private void printAllPatients() {
        List<Patient> patients = patientService.getAllPatients();
        System.out.println("Total Patients: " + patients.size());
        patients.forEach(System.out::println);
    }

    private void printAllDentists() {
        List<Dentist> dentists = dentistService.getAllDentists();
        System.out.println("Total Dentists: " + dentists.size());
        dentists.forEach(System.out::println);
    }

    private void printAllSurgeries() {
        List<Surgery> surgeries = surgeryService.getAllSurgeries();
        System.out.println("Total Surgeries: " + surgeries.size());
        surgeries.forEach(System.out::println);
    }

    private void printAllAppointments() {
        List<Appointment> appointments = appointmentService.getAllAppointments();
        System.out.println("Total Appointments: " + appointments.size());
        appointments.forEach(System.out::println);
    }
}
