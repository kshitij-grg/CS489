package edu.miu.cs.cs489.lab6.adsappointment.service;

import edu.miu.cs.cs489.lab6.adsappointment.model.Dentist;

import java.util.List;
import java.util.Optional;

public interface DentistService {
    List<Dentist> getAllDentists();
    Optional<Dentist> getDentistById(Integer dentistId);
    Optional<Dentist> getDentistByNo(String dentistNo);
    Dentist saveDentist(Dentist dentist);
    Dentist updateDentist(Dentist dentist);
    void deleteDentistById(Integer dentistId);
}
