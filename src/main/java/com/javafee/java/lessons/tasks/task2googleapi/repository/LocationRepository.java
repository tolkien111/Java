package com.javafee.java.lessons.tasks.task2googleapi.repository;

import com.javafee.java.lessons.tasks.task2googleapi.entity.LocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LocationRepository extends JpaRepository<LocationEntity, UUID> {

}
