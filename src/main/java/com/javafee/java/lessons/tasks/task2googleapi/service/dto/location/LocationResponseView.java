package com.javafee.java.lessons.tasks.task2googleapi.service.dto.location;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;


@Getter
@ToString
@NoArgsConstructor
public class LocationResponseView {
    private List<LocationIdView> locationIdViews;
    private LocationView locationView;

    public LocationResponseView(List<LocationIdView> locationIdViews) {
        this.locationIdViews = locationIdViews;
    }

    public LocationResponseView(LocationView locationView) {
        this.locationView = locationView;
    }

}
