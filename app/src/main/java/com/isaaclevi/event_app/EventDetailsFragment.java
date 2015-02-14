package com.isaaclevi.event_app;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class EventDetailsFragment extends Fragment {

    public interface EventDetailsFragmentDelegate {
        public void save();
    }

    private GoogleMap mMap;
    private Double latitude, longitude;

    Event event;
    EventDetailsFragmentDelegate delegate;

    public EventDetailsFragment() {
        // Required empty public constructor
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public void setEventDetailsDelegate(EventDetailsFragmentDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (container == null) {
            return null;
        }
        View view = inflater.inflate(R.layout.fragment_event_details, container, false);

        if(event != null) {
            Double coordinates[] = parseCoordinates(event.EventAddress);
            latitude = coordinates[0];
            longitude = coordinates[1];
        }

        setUpMapIfNeeded(); // For setting up the MapFragment

        return view;
    }

    private Double[] parseCoordinates(String eventAddress) {
        String result[] = eventAddress.split(",");
        Double coordinates[] = new Double[2];
        for(int i = 0; i <= 1; i++) {
            coordinates[i] = Double.parseDouble(result[i]);
        }
        return coordinates;
    }

    public void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            MapFragment mapFragment = (MapFragment) MainScreen.fragmentManager.findFragmentById(R.id.location_map);
            mMap = mapFragment.getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null)
                setUpMap();
        }
    }

    private void setUpMap() {
        // For showing a move to my location button
        mMap.setMyLocationEnabled(true);
        if(event != null) {
            // For dropping a marker at a point on the Map
            mMap.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude)).title(event.EventName).snippet("Event Address"));
            // For zooming automatically to the Dropped PIN Location
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude,
                    longitude), 12.0f));
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        if (mMap != null)
            setUpMap();
        else {
            // Try to obtain the map from the MapFragment.
            mMap = ((MapFragment) MainScreen.fragmentManager
                    .findFragmentById(R.id.location_map)).getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null)
                setUpMap();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mMap != null) {
            MainScreen.fragmentManager.beginTransaction()
                    .remove(MainScreen.fragmentManager.findFragmentById(R.id.location_map)).commit();
            mMap = null;
        }
    }
}
