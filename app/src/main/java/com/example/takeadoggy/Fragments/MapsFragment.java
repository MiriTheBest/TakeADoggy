package com.example.takeadoggy.Fragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.takeadoggy.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapsFragment extends Fragment{

    private GoogleMap myMap;
    private ArrayList<LatLng> locations = new ArrayList<LatLng>();
    private Marker[] mMarkers;
    FrameLayout mapView;

    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        @Override
        public void onMapReady(GoogleMap googleMap) {
            myMap = googleMap;

            mMarkers = new Marker[locations.size()];
            for (int i = 0; i < locations.size(); i++) {
                mMarkers[i] = myMap.addMarker(new MarkerOptions().position(locations.get(i)).title("Marker " + (i+1)));
            }
        }
    };


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_maps, container, false);
        findViews(view);
        initData();

        return view;
    }

    private void initData() {
        if(ListFragment.favDogsList != null) {
            for (int i = 0; i < ListFragment.favDogsList.size(); i++) {
                locations.add(new LatLng(ListFragment.favDogsList.get(i).getLatitude(), ListFragment.favDogsList.get(i).getLongitude()));
            }
        }
    }

    private void findViews(View view) {
        mapView = view.findViewById(R.id.map);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }

    public void zoomOnUserLocation(double latitude, double longitude) {
        LatLng location = new LatLng(latitude, longitude);
        myMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 12));
    }
}