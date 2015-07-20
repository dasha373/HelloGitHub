package com.courses.android.myproject;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


public class MainActivity extends ActionBarActivity implements OnMapReadyCallback, GoogleMap.OnMapLongClickListener {

    //second commit

    private GoogleMap mGoogleMap;
    private TextView mTextInfo;
    private MyLocationListener mMyLocationListener;
    private LocationManager mLocationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextInfo = (TextView) findViewById(R.id.tvMyLocationLatLng_AM);

        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        mMyLocationListener = new MyLocationListener(this);

        initMap();
    }

    private void initMap(){
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.googleMapFragment);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        if (mGoogleMap == null) return;
        mGoogleMap.getUiSettings().setZoomControlsEnabled(true);
        mGoogleMap.setMyLocationEnabled(true);
        mGoogleMap.setOnMapLongClickListener(this);
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(48.6179388, 22.2985991), 14));
        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        mGoogleMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            @Override
            public View getInfoWindow(Marker marker) {
                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {
                return null;
            }
        });
    }

    @Override
    public void onMapLongClick(LatLng latLng) {
        mGoogleMap.addMarker(new MarkerOptions()
                .position(latLng)
                .title("Marker #" + System.currentTimeMillis() % 10000)
                .snippet("My first MArker")
                .icon(BitmapDescriptorFactory.defaultMarker()));
    }

    public void setLocation(Location location){
        mTextInfo.setVisibility(View.VISIBLE);
        mTextInfo.setText(
                "Loc.: " + location.getLatitude() + " | " + location.getLongitude() + "\n" +
                        "Time: " + System.currentTimeMillis());
        onMapLongClick(new LatLng(location.getLatitude(), location.getLongitude()));
    }

    @Override
    protected void onStop() {
        super.onStop();
        mLocationManager.removeUpdates(mMyLocationListener);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mLocationManager.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER, 0, 0, mMyLocationListener);
    }
}
