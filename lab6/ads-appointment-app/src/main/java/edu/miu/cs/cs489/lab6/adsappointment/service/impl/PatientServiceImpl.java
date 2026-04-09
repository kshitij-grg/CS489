package edu.miu.cs.cs489.lab6.adsappointment.service.impl;

import edu.miu.cs.cs489.lab6.adsappointment.model.Patient;
import edu.miu.cs.cs489.lab6.adsappointment.repository.PatientRepository;
import edu.miu.cs.cs489.lab6.adsappointment.service.PatientService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @Override
    public Optional<Patient> getPatientById(Integer patientId) {
        return patientRepository.findById(patientId);
    }

    @Override
    public Optional<Patient> getPatientByNo(String patientNo) {
        return patientRepository.findByPatientNo(patientNo);
    }

    @Override
    public Patient savePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public Patient updatePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public void deletePatientById(Integer patientId) {
        patientRepository.deleteById(patientId);
    }
}
