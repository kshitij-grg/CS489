package edu.miu.cs.cs489.lab6.adsappointment.repository;

import edu.miu.cs.cs489.lab6.adsappointment.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Integer> {
    Optional<Patient> findByPatientNo(String patientNo);
}
