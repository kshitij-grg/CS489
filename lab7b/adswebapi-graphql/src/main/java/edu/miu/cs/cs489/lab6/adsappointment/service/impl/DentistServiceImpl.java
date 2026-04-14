package edu.miu.cs.cs489.lab6.adsappointment.service.impl;

import edu.miu.cs.cs489.lab6.adsappointment.model.Dentist;
import edu.miu.cs.cs489.lab6.adsappointment.repository.DentistRepository;
import edu.miu.cs.cs489.lab6.adsappointment.service.DentistService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DentistServiceImpl implements DentistService {

    private final DentistRepository dentistRepository;

    public DentistServiceImpl(DentistRepository dentistRepository) {
        this.dentistRepository = dentistRepository;
    }

    @Override
    public List<Dentist> getAllDentists() {
        return dentistRepository.findAll();
    }

    @Override
    public Optional<Dentist> getDentistById(Integer dentistId) {
        return dentistRepository.findById(dentistId);
    }

    @Override
    public Optional<Dentist> getDentistByNo(String dentistNo) {
        return dentistRepository.findByDentistNo(dentistNo);
    }

    @Override
    public Dentist saveDentist(Dentist dentist) {
        return dentistRepository.save(dentist);
    }

    @Override
    public Dentist updateDentist(Dentist dentist) {
        return dentistRepository.save(dentist);
    }

    @Override
    public void deleteDentistById(Integer dentistId) {
        dentistRepository.deleteById(dentistId);
    }
}
