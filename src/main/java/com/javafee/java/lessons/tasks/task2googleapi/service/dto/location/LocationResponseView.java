package com.javafee.java.lessons.tasks.task2googleapi.service.dto.location;


import lombok.Getter;
import lombok.ToString;

import java.util.List;


@Getter
@ToString
public class LocationResponseView {
    private List<LocationIdView> locationIdViews;
    private LocationView locationView;
    private final boolean isList;

    public LocationResponseView() {
        this.isList = false;
    }

    public LocationResponseView(List<LocationIdView> locationIdViews) {
        this.locationIdViews = locationIdViews;
        this.isList = true;
    }

    public LocationResponseView(LocationView locationView) {
        this.locationView = locationView;
        this.isList = false;
    }

    public boolean isList(){
        return isList;
    }
}
