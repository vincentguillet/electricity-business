package fr.vguillet.electricitybusiness.service.app;

import fr.vguillet.electricitybusiness.model.app.Address;
import fr.vguillet.electricitybusiness.repository.app.AddressRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    public Optional<Address> getAddressById(Long id) {
        return addressRepository.findById(id);
    }

    @Transactional
    public void saveAddress(Address address) {
        if (address.getId() == null) {
            addressRepository.save(address);
        }
    }

    @Transactional
    public Optional<Address> updateAddress(Address address) {
        return addressRepository.findById(address.getId()).map(existingAddress -> {
            existingAddress.setStreet(address.getStreet());
            existingAddress.setCity(address.getCity());
            existingAddress.setZipCode(address.getZipCode());
            existingAddress.setUsers(address.getUsers());
            return addressRepository.save(existingAddress);
        });
    }

    @Transactional
    public Optional<Address> deleteAddressById(Long id) {
        Optional<Address> address = addressRepository.findById(id);
        address.ifPresent(addressRepository::delete);
        return address;
    }
}
