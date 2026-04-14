package edu.miu.cs.cs489.lab6.adsappointment.repository;

import edu.miu.cs.cs489.lab6.adsappointment.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Integer> {
    Optional<Patient> findByPatientNo(String patientNo);

    List<Patient> findAllByOrderByLastNameAsc();

    @Query("""
            select p from Patient p
            where lower(concat(
                coalesce(p.patientNo, ''), ' ',
                coalesce(p.firstName, ''), ' ',
                coalesce(p.lastName, ''), ' ',
                coalesce(p.address.street, ''), ' ',
                coalesce(p.address.city, ''), ' ',
                coalesce(p.address.state, ''), ' ',
                coalesce(p.address.zipCode, '')
            )) like lower(concat('%', :searchString, '%'))
            order by p.lastName asc
            """)
    List<Patient> search(@Param("searchString") String searchString);

    @Query("""
            select p from Patient p
            order by p.address.city asc
            """)
    List<Patient> findAllOrderByAddressCityAsc();
}
