package edu.miu.cs.cs489.lab6.adsappointment.controller;

import edu.miu.cs.cs489.lab6.adsappointment.exception.PatientAlreadyExistsException;
import edu.miu.cs.cs489.lab6.adsappointment.exception.PatientNotFoundException;
import edu.miu.cs.cs489.lab6.adsappointment.graphql.input.NewAddress;
import edu.miu.cs.cs489.lab6.adsappointment.graphql.input.NewPatient;
import edu.miu.cs.cs489.lab6.adsappointment.model.Address;
import edu.miu.cs.cs489.lab6.adsappointment.model.Patient;
import edu.miu.cs.cs489.lab6.adsappointment.service.AddressService;
import edu.miu.cs.cs489.lab6.adsappointment.service.PatientService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class PatientGraphqlController {

    private final PatientService patientService;
    private final AddressService addressService;

    public PatientGraphqlController(PatientService patientService, AddressService addressService) {
        this.patientService = patientService;
        this.addressService = addressService;
    }

    @QueryMapping
    public List<Patient> allPatients() {
        return patientService.getAllPatientsSortedByLastName();
    }

    @QueryMapping
    public Patient patientById(@Argument Integer patientId) {
        return patientService.getPatientById(patientId)
                .orElseThrow(() -> new PatientNotFoundException("Patient with ID " + patientId + " not found"));
    }

    @QueryMapping
    public List<Patient> searchPatients(@Argument String searchString) {
        return patientService.searchPatients(searchString);
    }

    @MutationMapping
    public Patient addNewPatient(@Argument NewPatient newPatient) {
        patientService.getPatientByNo(newPatient.patientNo())
                .ifPresent(existing -> {
                    throw new PatientAlreadyExistsException("Patient number " + newPatient.patientNo() + " already exists");
                });

        Address savedAddress = addressService.saveAddress(toAddress(newPatient.primaryAddress()));
        Patient patient = new Patient(
                newPatient.patientNo(),
                newPatient.firstName(),
                newPatient.lastName(),
                savedAddress
        );
        return patientService.savePatient(patient);
    }

    @MutationMapping
    public Patient updatePatient(@Argument Integer patientId, @Argument NewPatient newPatient) {
        Patient patient = patientService.getPatientById(patientId)
                .orElseThrow(() -> new PatientNotFoundException("Patient with ID " + patientId + " not found"));

        patientService.getPatientByNo(newPatient.patientNo())
                .filter(existing -> !existing.getPatientId().equals(patientId))
                .ifPresent(existing -> {
                    throw new PatientAlreadyExistsException("Patient number " + newPatient.patientNo() + " already exists");
                });

        patient.setPatientNo(newPatient.patientNo());
        patient.setFirstName(newPatient.firstName());
        patient.setLastName(newPatient.lastName());

        Address patientAddress = patient.getAddress();
        if (patientAddress == null) {
            patientAddress = new Address();
        }

        NewAddress newAddress = newPatient.primaryAddress();
        patientAddress.setStreet(newAddress.street());
        patientAddress.setCity(newAddress.city());
        patientAddress.setState(newAddress.state());
        patientAddress.setZipCode(newAddress.zipCode());

        Address savedAddress = addressService.saveAddress(patientAddress);
        patient.setAddress(savedAddress);

        return patientService.updatePatient(patient);
    }

    @MutationMapping
    public String deletePatient(@Argument Integer patientId) {
        Patient patient = patientService.getPatientById(patientId)
                .orElseThrow(() -> new PatientNotFoundException("Patient with ID " + patientId + " not found"));

        patientService.deletePatientById(patient.getPatientId());
        return "Patient with ID " + patientId + " deleted successfully";
    }

    private Address toAddress(NewAddress request) {
        return new Address(
                request.street(),
                request.city(),
                request.state(),
                request.zipCode()
        );
    }
}
