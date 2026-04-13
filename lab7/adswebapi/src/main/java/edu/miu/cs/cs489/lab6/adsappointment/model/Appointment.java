package edu.miu.cs.cs489.lab6.adsappointment.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "appointments")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer appointmentId;

    @Column(nullable = false)
    private LocalDateTime appointmentDateTime;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dentist_id", nullable = false)
    private Dentist dentist;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "surgery_id", nullable = false)
    private Surgery surgery;

    public Appointment(LocalDateTime appointmentDateTime, Patient patient, Dentist dentist, Surgery surgery) {
        this.appointmentDateTime = appointmentDateTime;
        this.patient = patient;
        this.dentist = dentist;
        this.surgery = surgery;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "appointmentId=" + appointmentId +
                ", appointmentDateTime=" + appointmentDateTime +
                ", patient=" + (patient != null ? patient.getPatientNo() : "null") +
                ", dentist=" + (dentist != null ? dentist.getDentistNo() : "null") +
                ", surgery=" + (surgery != null ? surgery.getSurgeryNo() : "null") +
                '}';
    }
}
