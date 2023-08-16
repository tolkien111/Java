package com.javafee.java.lessons.tasks.task2googleapi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "locations")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Location {

    @Id
    private UUID id;
    private String addressDescription;
    private double latitude;
    private double longitude;

    public Location(String addressDescription, double latitude, double longitude) {
        this.id = UUID.randomUUID();
        this.addressDescription = addressDescription;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return Double.compare(location.latitude, latitude) == 0 && Double.compare(location.longitude, longitude) == 0 && Objects.equals(id, location.id) && Objects.equals(addressDescription, location.addressDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, addressDescription, latitude, longitude);
    }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", addressDescription='" + addressDescription + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
