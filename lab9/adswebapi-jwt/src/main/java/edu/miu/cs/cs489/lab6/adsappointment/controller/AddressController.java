package edu.miu.cs.cs489.lab6.adsappointment.controller;

import edu.miu.cs.cs489.lab6.adsappointment.dto.AddressWithPatientResponse;
import edu.miu.cs.cs489.lab6.adsappointment.dto.PatientSummaryResponse;
import edu.miu.cs.cs489.lab6.adsappointment.model.Address;
import edu.miu.cs.cs489.lab6.adsappointment.model.Patient;
import edu.miu.cs.cs489.lab6.adsappointment.service.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AddressController {

    private final PatientService patientService;

    public AddressController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/adsweb/api/v1/addresses")
    public ResponseEntity<List<AddressWithPatientResponse>> getAllAddresses() {
        List<AddressWithPatientResponse> addresses = patientService.getPatientsSortedByAddressCity()
                .stream()
                .map(this::toAddressWithPatientResponse)
                .toList();
        return ResponseEntity.ok(addresses);
    }

    private AddressWithPatientResponse toAddressWithPatientResponse(Patient patient) {
        Address address = patient.getAddress();
        return new AddressWithPatientResponse(
                address.getAddressId(),
                address.getStreet(),
                address.getCity(),
                address.getState(),
                address.getZipCode(),
                new PatientSummaryResponse(
                        patient.getPatientId(),
                        patient.getPatientNo(),
                        patient.getFirstName(),
                        patient.getLastName()
                )
        );
    }
}
