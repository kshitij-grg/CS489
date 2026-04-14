package edu.miu.cs.cs489.lab6.adsappointment.controller;

import edu.miu.cs.cs489.lab6.adsappointment.dto.AddressResponse;
import edu.miu.cs.cs489.lab6.adsappointment.dto.PatientRequest;
import edu.miu.cs.cs489.lab6.adsappointment.dto.PatientResponse;
import edu.miu.cs.cs489.lab6.adsappointment.exception.PatientAlreadyExistsException;
import edu.miu.cs.cs489.lab6.adsappointment.exception.PatientNotFoundException;
import edu.miu.cs.cs489.lab6.adsappointment.model.Address;
import edu.miu.cs.cs489.lab6.adsappointment.model.Patient;
import edu.miu.cs.cs489.lab6.adsappointment.service.AddressService;
import edu.miu.cs.cs489.lab6.adsappointment.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.List;

@RestController
@RequestMapping("/adsweb/api/v1")
public class PatientController {

    private final PatientService patientService;
    private final AddressService addressService;

    public PatientController(PatientService patientService, AddressService addressService) {
        this.patientService = patientService;
        this.addressService = addressService;
    }

    @GetMapping("/patients")
    public ResponseEntity<List<PatientResponse>> getAllPatients() {
        List<PatientResponse> patients = patientService.getAllPatientsSortedByLastName()
                .stream()
                .map(this::toPatientResponse)
                .toList();
        return ResponseEntity.ok(patients);
    }

    @GetMapping("/patients/{patientId}")
    public ResponseEntity<PatientResponse> getPatientById(@PathVariable Integer patientId) {
        Patient patient = patientService.getPatientById(patientId)
                .orElseThrow(() -> new PatientNotFoundException("Patient with ID " + patientId + " not found"));
        return ResponseEntity.ok(toPatientResponse(patient));
    }

    @PostMapping("/patients")
    public ResponseEntity<PatientResponse> createPatient(@Valid @RequestBody PatientRequest request) {
        patientService.getPatientByNo(request.patientNo())
                .ifPresent(existing -> {
                    throw new PatientAlreadyExistsException("Patient number " + request.patientNo() + " already exists");
                });

        Address address = toAddress(request);
        Address savedAddress = addressService.saveAddress(address);

        Patient patient = new Patient(request.patientNo(), request.firstName(), request.lastName(), savedAddress);
        Patient savedPatient = patientService.savePatient(patient);

        return new ResponseEntity<>(toPatientResponse(savedPatient), HttpStatus.CREATED);
    }

    @PutMapping("/patient/{patientId}")
    public ResponseEntity<PatientResponse> updatePatient(@PathVariable Integer patientId,
                                                         @Valid @RequestBody PatientRequest request) {
        Patient patient = patientService.getPatientById(patientId)
                .orElseThrow(() -> new PatientNotFoundException("Patient with ID " + patientId + " not found"));

        patientService.getPatientByNo(request.patientNo())
            .filter(existing -> !existing.getPatientId().equals(patientId))
            .ifPresent(existing -> {
                throw new PatientAlreadyExistsException("Patient number " + request.patientNo() + " already exists");
            });

        patient.setPatientNo(request.patientNo());
        patient.setFirstName(request.firstName());
        patient.setLastName(request.lastName());

        Address updatedAddress = patient.getAddress();
        if (updatedAddress == null) {
            updatedAddress = new Address();
        }
        updatedAddress.setStreet(request.primaryAddress().street());
        updatedAddress.setCity(request.primaryAddress().city());
        updatedAddress.setState(request.primaryAddress().state());
        updatedAddress.setZipCode(request.primaryAddress().zipCode());

        Address savedAddress = addressService.saveAddress(updatedAddress);
        patient.setAddress(savedAddress);

        Patient savedPatient = patientService.updatePatient(patient);
        return ResponseEntity.ok(toPatientResponse(savedPatient));
    }

    @DeleteMapping("/patient/{patientId}")
    public ResponseEntity<Map<String, Object>> deletePatient(@PathVariable Integer patientId) {
        Patient patient = patientService.getPatientById(patientId)
                .orElseThrow(() -> new PatientNotFoundException("Patient with ID " + patientId + " not found"));
        patientService.deletePatientById(patient.getPatientId());
        return ResponseEntity.ok(Map.of(
                "status", 200,
                "message", "Patient with ID " + patientId + " deleted successfully"
        ));
    }

    @GetMapping("/patient/search/{searchString}")
    public ResponseEntity<List<PatientResponse>> searchPatients(@PathVariable String searchString) {
        List<PatientResponse> patients = patientService.searchPatients(searchString)
                .stream()
                .map(this::toPatientResponse)
                .toList();
        return ResponseEntity.ok(patients);
    }

    private Address toAddress(PatientRequest request) {
        return new Address(
                request.primaryAddress().street(),
                request.primaryAddress().city(),
                request.primaryAddress().state(),
                request.primaryAddress().zipCode()
        );
    }

    private PatientResponse toPatientResponse(Patient patient) {
        Address address = patient.getAddress();
        AddressResponse primaryAddress = null;
        if (address != null) {
            primaryAddress = new AddressResponse(
                    address.getAddressId(),
                    address.getStreet(),
                    address.getCity(),
                    address.getState(),
                    address.getZipCode()
            );
        }

        return new PatientResponse(
                patient.getPatientId(),
                patient.getPatientNo(),
                patient.getFirstName(),
                patient.getLastName(),
                primaryAddress
        );
    }
}
