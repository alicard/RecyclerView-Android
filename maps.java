package com.example.bengkelku;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class maps extends FragmentActivity implements OnMapReadyCallback {

    EditText etSearch;
    Button btnSearch;
    static final LatLng ST3TELKOM_COORDINATE = new LatLng(-7.434952, 109.250123);
    static final LatLng BATURADEN_COORDINATE = new LatLng(-7.298936, 109.216753);

    private GoogleMap mMap;

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

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney, Australia, and move the camera.
        Marker markerST3=mMap.addMarker(new MarkerOptions().position(ST3TELKOM_COORDINATE).icon(BitmapDescriptorFactory.fromResource(R.drawable.common_full_open_on_phone)).title("ST3 TELKOM PURWOKERTO").snippet("Bridging Technology for Humanity"));
        Marker markerBaturaden=mMap.addMarker(new MarkerOptions().position(BATURADEN_COORDINATE).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)).title("BATURADEN").snippet("Tempat Wisata Purwokerto"));

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ST3TELKOM_COORDINATE, 30));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);
        mMap.getUiSettings().setZoomControlsEnabled(true);
    }

}

