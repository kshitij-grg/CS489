package edu.miu.cs.cs489.lab6.adsappointment.service.impl;

import edu.miu.cs.cs489.lab6.adsappointment.model.Patient;
import edu.miu.cs.cs489.lab6.adsappointment.repository.AppointmentRepository;
import edu.miu.cs.cs489.lab6.adsappointment.repository.PatientRepository;
import edu.miu.cs.cs489.lab6.adsappointment.service.PatientService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final AppointmentRepository appointmentRepository;

    public PatientServiceImpl(PatientRepository patientRepository,
                              AppointmentRepository appointmentRepository) {
        this.patientRepository = patientRepository;
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @Override
    public List<Patient> getAllPatientsSortedByLastName() {
        return patientRepository.findAllByOrderByLastNameAsc();
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
    public List<Patient> searchPatients(String searchString) {
        return patientRepository.search(searchString);
    }

    @Override
    public List<Patient> getPatientsSortedByAddressCity() {
        return patientRepository.findAllOrderByAddressCityAsc();
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
    @Transactional
    public void deletePatientById(Integer patientId) {
        appointmentRepository.deleteByPatientPatientId(patientId);
        patientRepository.deleteById(patientId);
    }
}
