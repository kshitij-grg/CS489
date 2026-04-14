package edu.miu.cs.cs489.lab6.adsappointment.service.impl;

import edu.miu.cs.cs489.lab6.adsappointment.model.Appointment;
import edu.miu.cs.cs489.lab6.adsappointment.repository.AppointmentRepository;
import edu.miu.cs.cs489.lab6.adsappointment.service.AppointmentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    @Override
    public Optional<Appointment> getAppointmentById(Integer appointmentId) {
        return appointmentRepository.findById(appointmentId);
    }

    @Override
    public List<Appointment> getAppointmentsByPatientNo(String patientNo) {
        return appointmentRepository.findByPatientPatientNo(patientNo);
    }

    @Override
    public List<Appointment> getAppointmentsByDentistNo(String dentistNo) {
        return appointmentRepository.findByDentistDentistNo(dentistNo);
    }

    @Override
    public Appointment saveAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    @Override
    public Appointment updateAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    @Override
    public void deleteAppointmentById(Integer appointmentId) {
        appointmentRepository.deleteById(appointmentId);
    }
}
