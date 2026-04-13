package edu.miu.cs.cs489.lab6.adsappointment.service;

import edu.miu.cs.cs489.lab6.adsappointment.model.Patient;

import java.util.List;
import java.util.Optional;

public interface PatientService {
    List<Patient> getAllPatients();
    List<Patient> getAllPatientsSortedByLastName();
    Optional<Patient> getPatientById(Integer patientId);
    Optional<Patient> getPatientByNo(String patientNo);
    List<Patient> searchPatients(String searchString);
    List<Patient> getPatientsSortedByAddressCity();
    Patient savePatient(Patient patient);
    Patient updatePatient(Patient patient);
    void deletePatientById(Integer patientId);
}
