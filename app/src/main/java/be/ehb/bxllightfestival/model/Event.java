package be.ehb.bxllightfestival.model;

import com.google.android.gms.maps.model.LatLng;

public class Event {

    private LatLng coordinate;
    private String event,info;

    public Event(LatLng coordinate, String event, String info) {
        this.coordinate = coordinate;
        this.event = event;
        this.info = info;
    }

    public LatLng getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(LatLng coordinate) {
        this.coordinate = coordinate;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
