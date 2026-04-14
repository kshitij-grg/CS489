package edu.miu.cs.cs489.lab6.adsappointment.exception;

public class PatientNotFoundException extends RuntimeException {
    public PatientNotFoundException(String message) {
        super(message);
    }
}
