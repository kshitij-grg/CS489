package edu.miu.cs.cs489.lab6.adsappointment.dto;

public record AddressWithPatientResponse(
        Integer addressId,
        String street,
        String city,
        String state,
        String zipCode,
        PatientSummaryResponse patient
) {
}
