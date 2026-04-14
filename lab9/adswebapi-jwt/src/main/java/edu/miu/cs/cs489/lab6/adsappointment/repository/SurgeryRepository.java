package edu.miu.cs.cs489.lab6.adsappointment.repository;

import edu.miu.cs.cs489.lab6.adsappointment.model.Surgery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SurgeryRepository extends JpaRepository<Surgery, Integer> {
    Optional<Surgery> findBySurgeryNo(String surgeryNo);
}
