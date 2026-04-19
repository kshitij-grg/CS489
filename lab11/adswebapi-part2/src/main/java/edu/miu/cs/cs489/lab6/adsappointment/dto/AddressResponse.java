package edu.miu.cs.cs489.lab6.adsappointment.dto;

public record AddressResponse(
        Integer addressId,
        String street,
        String city,
        String state,
        String zipCode
) {
}
