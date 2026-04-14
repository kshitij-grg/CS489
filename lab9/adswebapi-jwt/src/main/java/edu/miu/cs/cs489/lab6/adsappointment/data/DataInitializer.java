package edu.miu.cs.cs489.lab6.adsappointment.data;

import edu.miu.cs.cs489.lab6.adsappointment.model.*;
import edu.miu.cs.cs489.lab6.adsappointment.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class DataInitializer implements CommandLineRunner {

    private final AddressRepository addressRepository;
    private final PatientRepository patientRepository;
    private final DentistRepository dentistRepository;
    private final SurgeryRepository surgeryRepository;
    private final AppointmentRepository appointmentRepository;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(AddressRepository addressRepository,
                          PatientRepository patientRepository,
                          DentistRepository dentistRepository,
                          SurgeryRepository surgeryRepository,
                          AppointmentRepository appointmentRepository,
                          RoleRepository roleRepository,
                          UserRepository userRepository,
                          PasswordEncoder passwordEncoder) {
        this.addressRepository = addressRepository;
        this.patientRepository = patientRepository;
        this.dentistRepository = dentistRepository;
        this.surgeryRepository = surgeryRepository;
        this.appointmentRepository = appointmentRepository;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        System.out.println("\n========== Initializing ADS Appointment Database ==========\n");

        migrateLegacyUserPasswords();

        if (patientRepository.count() == 0) {
            System.out.println("Populating database with sample data...");
            initializeData();
            System.out.println("Database populated successfully!\n");
        } else {
            System.out.println("Database already populated. Skipping initialization.\n");
        }
    }

    private void migrateLegacyUserPasswords() {
        var users = userRepository.findAll();
        Role officeManagerRole = roleRepository.findByRoleName("Office Manager")
                .orElseGet(() -> roleRepository.save(new Role("Office Manager")));
        boolean updated = false;
        for (User user : users) {
            String existingPassword = user.getPassword();
            if (existingPassword != null && !existingPassword.startsWith("$2a$")
                    && !existingPassword.startsWith("$2b$")
                    && !existingPassword.startsWith("$2y$")) {
                user.setPassword(passwordEncoder.encode(existingPassword));
                updated = true;
            }

            if (!user.isAccountNonExpired() || !user.isAccountNonLocked()
                    || !user.isCredentialsNonExpired() || !user.isEnabled()) {
                user.setAccountNonExpired(true);
                user.setAccountNonLocked(true);
                user.setCredentialsNonExpired(true);
                user.setEnabled(true);
                updated = true;
            }

            if (user.getRoles() == null || user.getRoles().isEmpty()) {
                user.setRoles(new ArrayList<>(List.of(officeManagerRole)));
                updated = true;
            }
        }
        if (updated) {
            userRepository.saveAll(users);
            System.out.println("Legacy user data migrated for Spring Security compatibility.");
        }
    }

    private void initializeData() {
        // Create and save Addresses
        Address addr1 = addressRepository.save(new Address("123 Main St", "Springfield", "IL", "62701"));
        Address addr2 = addressRepository.save(new Address("456 Oak Ave", "Springfield", "IL", "62702"));
        Address addr3 = addressRepository.save(new Address("789 Elm St", "Springfield", "IL", "62703"));
        Address addr4 = addressRepository.save(new Address("321 Pine Rd", "Springfield", "IL", "62704"));
        Address addr5 = addressRepository.save(new Address("654 Maple Dr", "Springfield", "IL", "62705"));
        Address addr6 = addressRepository.save(new Address("987 Cedar Ln", "Springfield", "IL", "62706"));
        Address addr7 = addressRepository.save(new Address("159 Birch Ct", "Springfield", "IL", "62707"));
        Address addr8 = addressRepository.save(new Address("753 Walnut Way", "Springfield", "IL", "62708"));
        Address addr9 = addressRepository.save(new Address("456 Spruce St", "Springfield", "IL", "62709"));
        Address addr10 = addressRepository.save(new Address("789 Ash Ave", "Springfield", "IL", "62710"));
        Address addr11 = addressRepository.save(new Address("321 Oak Ln", "Springfield", "IL", "62711"));

        // Create Patients based on sample data - each with unique address
        Patient patient1 = new Patient("P100", "Gillian", "White", addr1);
        Patient patient2 = new Patient("P106", "Ian", "MacKay", addr2);
        Patient patient3 = new Patient("P108", "Ian", "MacKay", addr3);
        Patient patient4 = new Patient("P105", "Jill", "Bell", addr4);
        Patient patient5 = new Patient("P110", "John", "Walker", addr5);

        patientRepository.save(patient1);
        patientRepository.save(patient2);
        patientRepository.save(patient3);
        patientRepository.save(patient4);
        patientRepository.save(patient5);

        // Create Dentists based on sample data - each with unique address
        Dentist dentist1 = new Dentist("D001", "Tony", "Smith", addr6);
        Dentist dentist2 = new Dentist("D002", "Helen", "Pearson", addr7);
        Dentist dentist3 = new Dentist("D003", "Robin", "Plevin", addr8);

        dentistRepository.save(dentist1);
        dentistRepository.save(dentist2);
        dentistRepository.save(dentist3);

        // Create Surgeries based on sample data - each with unique address
        Surgery surgery1 = new Surgery("S10", "Dental Surgery 1", addr9);
        Surgery surgery2 = new Surgery("S15", "Dental Surgery 2", addr10);
        Surgery surgery3 = new Surgery("S13", "Dental Surgery 3", addr11);

        surgeryRepository.save(surgery1);
        surgeryRepository.save(surgery2);
        surgeryRepository.save(surgery3);

        // Create Appointments based on sample data
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yy HH:mm");

        // Tony Smith, P100, 12-Sep-13 10.00, S15
        Appointment app1 = new Appointment(
            LocalDateTime.parse("12-Sep-13 10:00", DateTimeFormatter.ofPattern("dd-MMM-yy HH:mm")),
            patient1, dentist1, surgery2
        );

        // Tony Smith, P106, 12-Sep-13 12.00, S15
        Appointment app2 = new Appointment(
            LocalDateTime.parse("12-Sep-13 12:00", DateTimeFormatter.ofPattern("dd-MMM-yy HH:mm")),
            patient2, dentist1, surgery2
        );

        // Helen Pearson, P108, 12-Sep-13 10.00, S10
        Appointment app3 = new Appointment(
            LocalDateTime.parse("12-Sep-13 10:00", DateTimeFormatter.ofPattern("dd-MMM-yy HH:mm")),
            patient3, dentist2, surgery1
        );

        // Helen Pearson, P108, 14-Sep-13 14.00, S10
        Appointment app4 = new Appointment(
            LocalDateTime.parse("14-Sep-13 14:00", DateTimeFormatter.ofPattern("dd-MMM-yy HH:mm")),
            patient3, dentist2, surgery1
        );

        // Robin Plevin, P105, 14-Sep-13 16.30, S15
        Appointment app5 = new Appointment(
            LocalDateTime.parse("14-Sep-13 16:30", DateTimeFormatter.ofPattern("dd-MMM-yy HH:mm")),
            patient4, dentist3, surgery2
        );

        // Robin Plevin, P110, 15-Sep-13 18.00, S13
        Appointment app6 = new Appointment(
            LocalDateTime.parse("15-Sep-13 18:00", DateTimeFormatter.ofPattern("dd-MMM-yy HH:mm")),
            patient5, dentist3, surgery3
        );

        appointmentRepository.save(app1);
        appointmentRepository.save(app2);
        appointmentRepository.save(app3);
        appointmentRepository.save(app4);
        appointmentRepository.save(app5);
        appointmentRepository.save(app6);

        // Create Roles
        Role officeManagerRole = roleRepository.findByRoleName("Office Manager")
            .orElseGet(() -> roleRepository.save(new Role("Office Manager")));

        // Create Users
        User user1 = new User("admin", passwordEncoder.encode("password123"), "John", "Doe", new ArrayList<>(List.of(officeManagerRole)));
        User user2 = new User("manager", passwordEncoder.encode("password123"), "Jane", "Smith", new ArrayList<>(List.of(officeManagerRole)));

        userRepository.save(user1);
        userRepository.save(user2);

        System.out.println("Sample data initialization completed.");
        System.out.println("  - 5 Patients created");
        System.out.println("  - 3 Dentists created");
        System.out.println("  - 3 Surgeries created");
        System.out.println("  - 6 Appointments created");
        System.out.println("  - 1 Role created");
        System.out.println("  - 2 Users created");
    }
}
