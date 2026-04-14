package edu.miu.cs.cs489.lab6.adsappointment.dto.userauth;

import jakarta.validation.constraints.NotBlank;

public record UserAuthRequest(
        @NotBlank(message = "Username cannot be null, empty or blankspace(s)")
        String username,
        @NotBlank(message = "Password cannot be null, empty or blankspace(s)")
        String password
) {
}