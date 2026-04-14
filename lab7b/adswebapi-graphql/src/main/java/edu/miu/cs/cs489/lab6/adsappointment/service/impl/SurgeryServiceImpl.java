package edu.miu.cs.cs489.lab6.adsappointment.service.impl;

import edu.miu.cs.cs489.lab6.adsappointment.model.Surgery;
import edu.miu.cs.cs489.lab6.adsappointment.repository.SurgeryRepository;
import edu.miu.cs.cs489.lab6.adsappointment.service.SurgeryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SurgeryServiceImpl implements SurgeryService {

    private final SurgeryRepository surgeryRepository;

    public SurgeryServiceImpl(SurgeryRepository surgeryRepository) {
        this.surgeryRepository = surgeryRepository;
    }

    @Override
    public List<Surgery> getAllSurgeries() {
        return surgeryRepository.findAll();
    }

    @Override
    public Optional<Surgery> getSurgeryById(Integer surgeryId) {
        return surgeryRepository.findById(surgeryId);
    }

    @Override
    public Optional<Surgery> getSurgeryByNo(String surgeryNo) {
        return surgeryRepository.findBySurgeryNo(surgeryNo);
    }

    @Override
    public Surgery saveSurgery(Surgery surgery) {
        return surgeryRepository.save(surgery);
    }

    @Override
    public Surgery updateSurgery(Surgery surgery) {
        return surgeryRepository.save(surgery);
    }

    @Override
    public void deleteSurgeryById(Integer surgeryId) {
        surgeryRepository.deleteById(surgeryId);
    }
}
