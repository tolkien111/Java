package com.javafee.java.lessons.tasks.task2googleapi.repository;

import com.javafee.java.lessons.tasks.task2googleapi.entity.Address;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
class AddressRepositoryTest {
    //Data for tests
    private final static Address address01 = new Address("Street01", "City01", "10-000", "PL", 1.00, 1.00);
    private final static Address address02 = new Address("Street02", "City02", "20-000", "EN", 2.00, 2.00);
    private final static Address address03 = new Address("Street03", "City03", "30-000", "DE", 3.00, 3.00);

    @Autowired
    private AddressRepository repository;


    @Test
    void shouldSaveInToDb() {
        //GIVEN
        //WHEN
        repository.saveAllAndFlush(List.of(address01, address02, address03));
        //THEN
        int result = 3;
        assertEquals(result, repository.count());
        assertTrue(repository.findById(address01.getId()).isPresent());
        assertEquals(address01, repository.getReferenceById(address01.getId()));
    }
}