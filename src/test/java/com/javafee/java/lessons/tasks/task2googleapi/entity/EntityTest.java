package com.javafee.java.lessons.tasks.task2googleapi.entity;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

@SpringBootTest
@Transactional
@Profile("test")
abstract class EntityTest {

    @Autowired
    private EntityManager entityManager;

    protected void persist(Object entity){
        entityManager.persist(entity);
        entityManager.flush();
        entityManager.clear();
    }
}
