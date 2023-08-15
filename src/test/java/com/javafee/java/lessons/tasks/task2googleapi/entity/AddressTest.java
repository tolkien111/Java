package com.javafee.java.lessons.tasks.task2googleapi.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddressTest extends EntityTest {

    @Test
    void shouldSaveAddressIntoDb() {
        //GIVEN
        final var address = new Address("Street", "City", "00-000", "PL", 0.00, 0.00);
        //WHEN
        persistDb(address);

        //THEN
        final var readAddress = em.find(Address.class, address.getId());
        assertEquals(address, readAddress);

    }

}