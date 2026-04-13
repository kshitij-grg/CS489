package edu.miu.cs.cs489.lab6.adsappointment.service;

import edu.miu.cs.cs489.lab6.adsappointment.model.Appointment;

import java.util.List;
import java.util.Optional;

public interface AppointmentService {
    List<Appointment> getAllAppointments();
    Optional<Appointment> getAppointmentById(Integer appointmentId);
    List<Appointment> getAppointmentsByPatientNo(String patientNo);
    List<Appointment> getAppointmentsByDentistNo(String dentistNo);
    Appointment saveAppointment(Appointment appointment);
    Appointment updateAppointment(Appointment appointment);
    void deleteAppointmentById(Integer appointmentId);
}
