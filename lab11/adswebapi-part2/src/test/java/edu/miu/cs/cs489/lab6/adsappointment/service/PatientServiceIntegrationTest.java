package edu.miu.cs.cs489.lab6.adsappointment.service;

import edu.miu.cs.cs489.lab6.adsappointment.data.DataInitializer;
import edu.miu.cs.cs489.lab6.adsappointment.model.Address;
import edu.miu.cs.cs489.lab6.adsappointment.model.Patient;
import edu.miu.cs.cs489.lab6.adsappointment.repository.AddressRepository;
import edu.miu.cs.cs489.lab6.adsappointment.repository.PatientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class PatientServiceIntegrationTest {

    @Autowired
    private PatientService patientService;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private AddressRepository addressRepository;

    @MockBean
    private DataInitializer dataInitializer;

    @Test
    void findPatientById_existingPatientId_returnsPatient() {
        Address address = addressRepository.save(new Address("10 Test St", "Fairfield", "IA", "52557"));
        String patientNo = "IT-" + UUID.randomUUID().toString().substring(0, 8);
        Patient savedPatient = patientRepository.save(new Patient(patientNo, "Test", "Patient", address));

        Optional<Patient> actual = patientService.findPatientById(savedPatient.getPatientId());

        assertTrue(actual.isPresent());
        assertEquals(savedPatient.getPatientId(), actual.get().getPatientId());
        assertEquals(patientNo, actual.get().getPatientNo());
    }

    @Test
    void findPatientById_invalidPatientId_returnsEmpty() {
        Optional<Patient> actual = patientService.findPatientById(Integer.MAX_VALUE);

        assertTrue(actual.isEmpty());
    }
}
