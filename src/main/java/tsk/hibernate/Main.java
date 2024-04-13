package tsk.hibernate;

import tsk.hibernate.model.Address;
import tsk.hibernate.service.AddressService;

public class Main {
    public static void main(String[] args) {

        AddressService addressService = new AddressService();

        addressService.createTable();

        Address addr1 = Address.builder()
                .postalCode(123456)
                .city("Пекин")
                .house(34)
                .nameStreet("Ванфуцзин")
                .build();

        Address addr2 = Address.builder()
                .postalCode(112233)
                .city("Таллин")
                .house(187)
                .nameStreet("Лаборатоориуми")
                .build();

        Address addr3 = Address.builder()
                .postalCode(034525)
                .city("Рига")
                .house(4)
                .nameStreet("Малпилс")
                .build();

        Address addr4 = Address.builder()
                .postalCode(346712)
                .city("Паттайя")
                .house(12)
                .nameStreet("Сукхумвит Роад")
                .build();

//        addressService.saveAddress(addr1);
//        addressService.saveAddress(addr2);
//        addressService.saveAddress(addr3);
//        addressService.saveAddress(addr4);

        System.out.println(addressService.getAddressById(2L));
    }
}
