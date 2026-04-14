package edu.miu.cs.cs489.lab6.adsappointment.service;

import edu.miu.cs.cs489.lab6.adsappointment.model.Surgery;

import java.util.List;
import java.util.Optional;

public interface SurgeryService {
    List<Surgery> getAllSurgeries();
    Optional<Surgery> getSurgeryById(Integer surgeryId);
    Optional<Surgery> getSurgeryByNo(String surgeryNo);
    Surgery saveSurgery(Surgery surgery);
    Surgery updateSurgery(Surgery surgery);
    void deleteSurgeryById(Integer surgeryId);
}
