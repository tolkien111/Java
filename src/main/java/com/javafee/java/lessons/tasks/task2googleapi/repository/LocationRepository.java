package com.javafee.java.lessons.tasks.task2googleapi.repository;

import com.javafee.java.lessons.tasks.task2googleapi.entity.LocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface LocationRepository extends JpaRepository<LocationEntity, UUID> {

    //for searching location in db
    @Query("FROM LocationEntity l WHERE l.latitude = ?1 AND l.longitude = ?2")
    LocationEntity readLocation(String latitude, String longitude);

    @Query("SELECT (count(l) > 0) FROM LocationEntity l WHERE l.latitude = ?1 AND l.longitude = ?2")
    boolean locationExists(String latitude, String longitude);
}
