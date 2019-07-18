package com.ygor.javajdbc.repository;

import com.ygor.javajdbc.model.Address;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class AddressRepositoryTest {

    private AddressRepository addressRepository = new AddressRepository();

    @Test
    public void getId() {
        int user_id = 1;

        Address address = addressRepository.getById(user_id);

        assertEquals(user_id, address.getUserId());

        printAddress(address);
    }

    @Test
    public void getAll() {
        List<Address> addresses = addressRepository.getAll();

        assertNotNull(addresses);

        for (Address address : addresses) {
            printAddress(address);
            System.out.println("--------------------------------");
        }

    }

    @Test
    public void create() {
        Address address = new Address(
                4, "Rua Brasília 64", "João Pessoa",
                "PB", "Brasil", "58078-458");

        Address savedAddress = addressRepository.create(address);
        Address newAddress = addressRepository.getById(savedAddress.getUserId());

        assertNotNull(newAddress);
        assertEquals("Rua Brasília 64", newAddress.getStreet());
        assertEquals("João Pessoa", newAddress.getCity());
        assertEquals("PB", newAddress.getState());
        assertEquals("Brasil", newAddress.getCountry());
        assertEquals("58078-458", newAddress.getZipCode());

        printAddress(newAddress);
    }

    @Test
    public void update() {
        int user_id = 1;

        Address updateAddress = addressRepository.getById(user_id);

        updateAddress.setCountry("United States");

        addressRepository.update(user_id, updateAddress);

        Address newAddress = addressRepository.getById(user_id);

        assertNotNull(newAddress);
        assertEquals(updateAddress.getCountry(), newAddress.getCountry());

        printAddress(newAddress);
    }

    @Test
    public void remove() {
        int user_id = 3;

        addressRepository.remove(user_id);

        Address address = addressRepository.getById(user_id);

        assertNull(address);
    }

    private void printAddress(Address address) {
        System.out.println("STREET: " + address.getStreet());
        System.out.println("CITY: " + address.getCity());
        System.out.println("STATE: " + address.getState());
        System.out.println("COUNTRY: " + address.getCountry());
        System.out.println("ZIP CODE: " + address.getZipCode());
    }
}
