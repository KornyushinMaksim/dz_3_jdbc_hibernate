package tsk.hibernate.service;

import org.springframework.stereotype.Service;
import tsk.hibernate.model.Address;
import tsk.hibernate.repository.AddressRepository;
import tsk.hibernate.repository.AddressRepositoryImpl;

import java.util.List;

@Service
public class AddressService {

    public final AddressRepository addressRepository = new AddressRepositoryImpl();

    public void createTable() {
        addressRepository.createTable();
    };
    public void dropTable() {
        addressRepository.dropTable();
    };
    public void saveAddress(Address address) {
        addressRepository.saveAddress(address);
    };
    public void removeAddressById(Long id) {
        addressRepository.removeAddressById(id);
    };
    public void updateAddress(Address address) {
        addressRepository.updateAddress(address);
    };
    public Address getAddressById(Long id) {
        return addressRepository.getAddressById(id);
    };
    public List<Address> getAllAddresses() {
        return addressRepository.getAllAddresses();
    };
}
