package edu.miu.cs.cs489.lab6.adsappointment.dto;

public record PatientSummaryResponse(
        Integer patientId,
        String patientNo,
        String firstName,
        String lastName
) {
}
