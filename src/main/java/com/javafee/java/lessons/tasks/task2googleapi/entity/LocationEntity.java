package com.javafee.java.lessons.tasks.task2googleapi.entity;

import com.javafee.java.lessons.tasks.task2googleapi.service.dto.LocationView;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "locations")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Data
public class LocationEntity {

    @Id
    @Builder.Default
    private UUID id = UUID.randomUUID();
    private String addressDescription;
    private String latitude;
    private String longitude;

    public LocationView toView(){
        return new LocationView(addressDescription, latitude, longitude);
    }
}
