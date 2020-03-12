package be.ehb.bxllightfestival.model;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

public class EventDAO {

    public static EventDAO INSTANCE = new EventDAO();

    private ArrayList<Event> events;

    private EventDAO(){

    }

    public ArrayList<Event> getEvents(){
        if (events == null){
            events = new ArrayList<>();
            events.add(new Event(new LatLng(50.858712, 4.347446),"Kaaitheather", "Boot met vuurwerk"));
            events.add(new Event(new LatLng(50.860215, 4.350880),"Maximiliaanpark", "Interactieve projectie"));
            events.add(new Event(new LatLng(50.863994, 4.349828),"Magasin4", "Lasershow"));
            events.add(new Event(new LatLng(50.846777, 4.352360),"Grote markt", "Belichting gewouw  over geschiedenis van Brussel"));

        }
        return events;
    }
}
