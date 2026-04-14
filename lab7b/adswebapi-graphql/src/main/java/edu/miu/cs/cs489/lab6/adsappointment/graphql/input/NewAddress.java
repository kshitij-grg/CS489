package edu.miu.cs.cs489.lab6.adsappointment.graphql.input;

public record NewAddress(
        String street,
        String city,
        String state,
        String zipCode
) {
}
