package com.javafee.java.lessons.tasks.task2googleapi.entity;

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
    @NonNull
    private String addressDescription;
    @NonNull
    private String latitude;
    @NonNull
    private String longitude;

}
