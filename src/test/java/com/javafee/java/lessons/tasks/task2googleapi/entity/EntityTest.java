package com.javafee.java.lessons.tasks.task2googleapi.entity;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
abstract class EntityTest {

    @Autowired
    protected EntityManager entityManager;

    protected void persist(Object entity){
        entityManager.persist(entity);
        entityManager.flush();
        entityManager.clear();
    }
}
