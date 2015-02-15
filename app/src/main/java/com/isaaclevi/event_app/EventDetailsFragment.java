package com.isaaclevi.event_app;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class EventDetailsFragment extends Fragment {

    public interface EventDetailsFragmentDelegate {
        public void save();
    }

    MapView mapView;
    private GoogleMap googleMap;
    private Double latitude, longitude;
    private MarkerOptions marker;

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

    private Double[] parseCoordinates(String eventAddress) {
        String result[] = eventAddress.split(",");
        Double coordinates[] = new Double[2];
        for(int i = 0; i <= 1; i++) {
            coordinates[i] = Double.parseDouble(result[i]);
        }
        return coordinates;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // inflate and return the layout
        View v = inflater.inflate(R.layout.fragment_event_details, container, false);
        mapView = (MapView) v.findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);

        mapView.onResume();// needed to get the map to display immediately

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        googleMap = mapView.getMap();
        marker = new MarkerOptions();

        if(event.EventAddress != null && event.EventName != null) {
            Double coordinates[] = parseCoordinates(event.EventAddress);
            latitude = coordinates[0];
            longitude = coordinates[1];
            // create marker
            setMapLocation(new LatLng(latitude, longitude));
            marker.title(event.EventName);
        }

        googleMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                setMapLocation(latLng);
            }
        });

        return v;
    }

    private void setMapLocation(LatLng latLng) {
        // set marker position
        marker.position(latLng);
        // adding marker
        googleMap.addMarker(marker);
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(latLng).zoom(12).build();
        googleMap.animateCamera(CameraUpdateFactory
                .newCameraPosition(cameraPosition));
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
