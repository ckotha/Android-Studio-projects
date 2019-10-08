package com.charan.hikerswatch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    LocationManager locationManager;
    LocationListener locationListener;
    TextView locationInfo;
    String info = "";

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            listen();

        }
    }


    public void listen(){

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        }

    }

    public void updateLocationInfo(Location location){

        Log.i("LocationInfo", location.toString());

        Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());

        try {

            List<Address> addressList = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);

            if(addressList.get(0) != null) {

                DecimalFormat df = new DecimalFormat("#.######");
                df.setRoundingMode(RoundingMode.CEILING);
                String lat = "";
                String lng = "";
                if(addressList.get(0).getLatitude() >= 0 ){
                    lat = " °N";
                }else{
                    lat = " °S";
                }

                if(addressList.get(0).getLongitude() >= 0){
                    lng = " °E";
                }else{
                    lng = " °W";
                }

                info = "Latitude: " + df.format(addressList.get(0).getLatitude()) + lat + "\n"
                        + "Longitude: " +  df.format(addressList.get(0).getLongitude()) + lng + "\n"
                        + "Accuracy: " +  df.format(location.getAccuracy()) + "m" + "\n"
                        + "Altitude: " +  df.format(location.getAltitude()) + "m" + "\n"
                        + "Address: " + addressList.get(0).getAddressLine(0);
            }

            Log.i("Info",info);



        } catch (IOException e) {

            e.printStackTrace();

        }

        locationInfo.setText(info);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        locationInfo = findViewById(R.id.locationInfo);

        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

                updateLocationInfo(location);

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
        };
        if (Build.VERSION.SDK_INT < 23) {

            listen();
        } else {

            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                // ask for permission

                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);


            } else {

                // we have permission!

                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);

                Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

                if (location != null) {

                    updateLocationInfo(location);

                }

            }

        }

    }
}
