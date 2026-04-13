package edu.miu.cs.cs489.lab6.adsappointment.repository;

import edu.miu.cs.cs489.lab6.adsappointment.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
    List<Appointment> findByPatientPatientNo(String patientNo);
    List<Appointment> findByDentistDentistNo(String dentistNo);
    void deleteByPatientPatientId(Integer patientId);
}
