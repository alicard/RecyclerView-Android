package com.example.bengkelku;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import java.io.IOException;
import java.util.List;

import static com.example.bengkelku.MainActivity.bengkel;

public class maps extends FragmentActivity implements OnMapReadyCallback {

    EditText etSearch;
    Button btnSearch;

    private GoogleMap mMap;
    CameraPosition cameraPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        etSearch = (EditText) findViewById(R.id.tvSearch);
        btnSearch = (Button) findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String location = etSearch.getText().toString();
                if (location != null) {
                    setNewLocation(location);
                } else {
                    Toast.makeText(getBaseContext(), "MASUKKAN LOKASI", Toast.LENGTH_LONG).show();
                }
            }
        });

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    private void setNewLocation(String location) {
        Geocoder geocoder = new Geocoder(getBaseContext());
        try {
            mMap.clear();
            List<Address> addresses = geocoder.getFromLocationName(location, 10);
            Address address = addresses.get(0);
            LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
            Marker marker = mMap.addMarker(new MarkerOptions()
                    .position(latLng)
                    .title(address.getAddressLine(0)));
            mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // This Error Solved
    public void getMarker() {
        for (Bengkel data : bengkel) {
            LatLng latlng = new LatLng(Double.parseDouble(data.getLat()), Double.parseDouble(data.getLng()));

            mMap.addMarker(new MarkerOptions().position(latlng).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng defaultZoom = new LatLng(-7.759708, 110.408932);

        // Add a marker in Sydney, Australia, and move the camera.
        cameraPosition = new CameraPosition.Builder().target(defaultZoom).zoom(10).build();
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(defaultZoom, 17));
        mMap.getUiSettings().setZoomControlsEnabled(true);

        getMarker();
    }
}
