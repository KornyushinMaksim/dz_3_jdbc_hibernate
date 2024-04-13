package tsk.hibernate.repository;

import org.springframework.stereotype.Repository;
import tsk.hibernate.model.Address;

import java.util.List;

@Repository
public interface AddressRepository {

    void createTable();
    void dropTable();
    void saveAddress(Address address);
    void removeAddressById(Long id);
    void updateAddress(Address address);
    Address getAddressById(Long id);
    List<Address> getAllAddresses();

}
