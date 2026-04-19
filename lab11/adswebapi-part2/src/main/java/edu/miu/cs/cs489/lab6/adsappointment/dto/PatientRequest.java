package edu.miu.cs.cs489.lab6.adsappointment.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PatientRequest(
        @NotBlank(message = "Patient number is required") String patientNo,
        @NotBlank(message = "First name is required") String firstName,
        @NotBlank(message = "Last name is required") String lastName,
        @NotNull(message = "Primary address is required") @Valid AddressRequest primaryAddress
) {
}
