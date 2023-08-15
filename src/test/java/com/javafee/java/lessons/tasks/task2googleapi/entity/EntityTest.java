package com.javafee.java.lessons.tasks.task2googleapi.entity;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Transactional
public class EntityTest {

    @Autowired
    protected EntityManager em;

    protected void persistDb(Object entity){
        em.persist(entity); // add to cache
        em.flush(); // add to db from cache
        em.clear(); // cleat cache
    }
}
