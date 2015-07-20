package com.courses.android.myproject;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

/**
 * Created by Asus on 20.07.2015.
 */
public class MyLocationListener implements LocationListener {
    private MainActivity mMainActivity;
    public MyLocationListener(MainActivity _mainActivity) {
        this.mMainActivity = _mainActivity;
    }
    @Override
    public void onLocationChanged(Location location) {
        if (mMainActivity != null)
            this.mMainActivity.setLocation(location);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
