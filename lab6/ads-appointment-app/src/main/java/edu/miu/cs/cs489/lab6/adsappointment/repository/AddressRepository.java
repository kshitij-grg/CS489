package edu.miu.cs.cs489.lab6.adsappointment.repository;

import edu.miu.cs.cs489.lab6.adsappointment.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
