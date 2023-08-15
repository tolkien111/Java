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
@Table(name = "addresses")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Address {

    @Id
    private UUID id;
    private String street;
    private String city;
    private String postalCode;
    private String countyCode;
    private double latitude;
    private double longitude;

    public Address(String street, String city, String postalCode, String countyCode, double latitude, double longitude) {
        this.id = UUID.randomUUID();
        this.street = street;
        this.city = city;
        this.postalCode = postalCode;
        this.countyCode = countyCode;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", countyCode='" + countyCode + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Double.compare(address.latitude, latitude) == 0 && Double.compare(address.longitude, longitude) == 0 && Objects.equals(id, address.id) && Objects.equals(street, address.street) && Objects.equals(city, address.city) && Objects.equals(postalCode, address.postalCode) && Objects.equals(countyCode, address.countyCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, street, city, postalCode, countyCode, latitude, longitude);
    }
}
