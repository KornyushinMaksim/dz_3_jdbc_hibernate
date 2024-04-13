package tsk.hibernate.repository;

import tsk.hibernate.model.Address;

import java.util.List;

public interface AddressRepository {

    void createTable();
    void dropTable();
    void saveAddress(Address address);
    void removeAddressById(Long id);
    void updateAddress(Address address);
    Address getAddressById(Long id);
    List<Address> getAllAddresses();

}
