package edu.miu.cs.cs489.lab6.adsappointment.service.impl;

import edu.miu.cs.cs489.lab6.adsappointment.model.Address;
import edu.miu.cs.cs489.lab6.adsappointment.repository.AddressRepository;
import edu.miu.cs.cs489.lab6.adsappointment.service.AddressService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public Address saveAddress(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public Optional<Address> getAddressById(Integer addressId) {
        return addressRepository.findById(addressId);
    }
}
