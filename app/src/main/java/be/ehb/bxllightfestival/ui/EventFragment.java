package be.ehb.bxllightfestival.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import be.ehb.bxllightfestival.R;
import be.ehb.bxllightfestival.model.Event;
import be.ehb.bxllightfestival.model.EventDAO;

public class EventFragment extends Fragment {

    //fields
    private MapView mapView;
    private GoogleMap myMap;

    //listeners
    private OnMapReadyCallback onMapReady = new OnMapReadyCallback() {
        @Override
        public void onMapReady(GoogleMap googleMap) {
            //Field maken om Googlemap instantie in andere methoden te krijgen
            myMap = googleMap;

            //kaart klaar
            //kaart centreren op een coördinaat
            LatLng coordBrussel = new LatLng(50.858712, 4.347446);

            CameraUpdate moveToBrussel = CameraUpdateFactory.newLatLngZoom(coordBrussel,16);

            myMap.animateCamera(moveToBrussel);
            myMap.setMapType(googleMap.MAP_TYPE_SATELLITE);

            myMap.setOnInfoWindowClickListener(infoWindowClickListener);
            setMarkerAdapter();
            drawMarkers();




        }
    };

    private void setMarkerAdapter() {
        myMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            @Override
            public View getInfoWindow(Marker marker) {
                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {
                View cardView = getActivity().getLayoutInflater().inflate(R.layout.marker_card, null, false);

                TextView tvTitle = cardView.findViewById(R.id.tv_card_title);
                TextView tvSnippet = cardView.findViewById(R.id.tv_snippet);

                tvTitle.setText(marker.getTitle());
                tvSnippet.setText(marker.getSnippet());



                return cardView;
            }
        });
    }

    private void drawMarkers() {

        myMap.addMarker(new MarkerOptions()
        .position(new LatLng(50.858712, 4.347446))
        .title("Kaaitheater")
        .snippet("Boot met vuurwerk")
        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));

        myMap.addMarker(new MarkerOptions()
                .position(new LatLng(50.860215, 4.350880))
                .title("Maximiliaan park")
                .snippet("interactieve projectie")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));

        myMap.addMarker(new MarkerOptions()
                .position(new LatLng(50.863994, 4.349828))
                .title("Magasin4")
                .snippet("Lasershow")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));

        myMap.addMarker(new MarkerOptions()
                .position(new LatLng(50.846777, 4.352360))
                .title("Grote markt")
                .snippet("Belichting gebouw om geschiedenis van Brussel voor te stellen")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));




        for (Event event : EventDAO.INSTANCE.getEvents()){
            Marker m = myMap.addMarker(new MarkerOptions().position(event.getCoordinate()));
            m.setTitle(event.getEvent());
            m.setSnippet(event.getInfo());
            m.setTag(event);

        }

    }

    private GoogleMap.OnInfoWindowClickListener infoWindowClickListener = new GoogleMap.OnInfoWindowClickListener() {
        @Override
        public void onInfoWindowClick(Marker marker) {
            Event e = (Event) marker.getTag();
            if (e != null)
                Toast.makeText(getActivity(),e.getInfo(),Toast.LENGTH_SHORT).show();
        }
    };
     public EventFragment() {

     }


    //Lifecycle
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       View rootView = inflater.inflate(R.layout.fragment_event,container, false);

       mapView = rootView.findViewById(R.id.mapView);
       mapView.onCreate(savedInstanceState);
       mapView.getMapAsync(onMapReady);


        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }
}



