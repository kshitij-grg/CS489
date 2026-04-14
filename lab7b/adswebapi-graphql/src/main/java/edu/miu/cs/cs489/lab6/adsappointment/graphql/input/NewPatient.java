package edu.miu.cs.cs489.lab6.adsappointment.graphql.input;

public record NewPatient(
        String patientNo,
        String firstName,
        String lastName,
        NewAddress primaryAddress
) {
}
