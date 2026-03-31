package edu.miu.cs489.lab2b;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import edu.miu.cs489.lab2b.model.Patient;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PAMSApp {

    public static void main(String[] args) throws IOException {
        List<Patient> patients = new ArrayList<>();

        patients.add(new Patient(1, "Daniel", "Agar", "(641) 123-0009", "dagar@m.as", "1 N Street", LocalDate.of(1987, 1, 19)));
        patients.add(new Patient(2, "Ana", "Smith", "", "amsith@te.edu", "", LocalDate.of(1948, 12, 5)));
        patients.add(new Patient(3, "Marcus", "Garvey", "(123) 292-0018", "", "4 East Ave", LocalDate.of(2001, 9, 18)));
        patients.add(new Patient(4, "Jeff", "Goldbloom", "(999) 165-1192", "jgold@es.co.za", "", LocalDate.of(1995, 2, 28)));
        patients.add(new Patient(5, "Mary", "Washington", "", "", "30 W Burlington", LocalDate.of(1932, 5, 31)));

        List<Patient> sortedPatients = patients.stream()
                .sorted(Comparator.comparingInt(Patient::getAge).reversed())
                .toList();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        Path outputDir = Path.of("output");
        Files.createDirectories(outputDir);
        Path outputFile = outputDir.resolve("patients.json");

        objectMapper.writeValue(outputFile.toFile(), sortedPatients);
        System.out.println("Patients JSON written to: " + outputFile.toAbsolutePath());
    }
}
