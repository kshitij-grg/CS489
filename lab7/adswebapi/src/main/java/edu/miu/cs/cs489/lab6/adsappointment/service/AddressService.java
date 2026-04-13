package edu.miu.cs.cs489.lab6.adsappointment.service;

import edu.miu.cs.cs489.lab6.adsappointment.model.Address;

import java.util.Optional;

public interface AddressService {
    Address saveAddress(Address address);
    Optional<Address> getAddressById(Integer addressId);
}
