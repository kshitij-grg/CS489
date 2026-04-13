package edu.miu.cs.cs489.lab6.adsappointment.repository;

import edu.miu.cs.cs489.lab6.adsappointment.model.Dentist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DentistRepository extends JpaRepository<Dentist, Integer> {
    Optional<Dentist> findByDentistNo(String dentistNo);
}
