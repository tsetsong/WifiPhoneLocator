package com.myapplication5example.getlocation;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class GetLocationMainActivity extends Activity {

    double nlat;
    double nlng;
    double glat;
    double glng;

    LocationManager glocManager;
    LocationListener glocListener;
    LocationManager nlocManager;
    LocationListener nlocListener;

    TextView textViewNetLat;
    TextView textViewNetLng;
    TextView textViewGpsLat;
    TextView textViewGpsLng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_location_main);

        //All textView
        textViewNetLat = (TextView)findViewById(R.id.textViewNetLat);
        textViewNetLng = (TextView)findViewById(R.id.textViewNetLng);
        textViewGpsLat = (TextView)findViewById(R.id.textViewGpsLat);
        textViewGpsLng = (TextView)findViewById(R.id.textViewGpsLng);
    }

    @Override
    public void onDestroy() {

        //Remove GPS location update
        if(glocManager != null){
            glocManager.removeUpdates(glocListener);
            Log.d("ServiceForLatLng", "GPS Update Released");
        }

        //Remove Network location update
        if(nlocManager != null){
            nlocManager.removeUpdates(nlocListener);
            Log.d("ServiceForLatLng", "Network Update Released");
        }
        super.onDestroy();
    }

    //This is for Lat lng which is determine by your wireless or mobile network
    public class MyLocationListenerNetWork implements LocationListener
    {
        @Override
        public void onLocationChanged(Location loc)
        {
            nlat = loc.getLatitude();
            nlng = loc.getLongitude();

            //Setting the Network Lat, Lng into the textView
            textViewNetLat.setText("Network Latitude:  " + nlat);
            textViewNetLng.setText("Network Longitude:  " + nlng);

            Log.d("LAT & LNG Network:", nlat + " " + nlng);
        }

        @Override
        public void onProviderDisabled(String provider)
        {
            Log.d("LOG", "Network is OFF!");
        }
        @Override
        public void onProviderEnabled(String provider)
        {
            Log.d("LOG", "Thanks for enabling Network !");
        }
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras)
        {
        }
    }

    //This is for Lat lng which is determine by your device GPS
    public class MyLocationListenerGPS implements LocationListener
    {
        @Override
        public void onLocationChanged(Location loc)
        {
            glat = loc.getLatitude();
            glng = loc.getLongitude();

            //Setting the GPS Lat, Lng into the textView
            textViewGpsLat.setText("GPS Latitude:  " + glat);
            textViewGpsLng.setText("GPS Longitude:  " + glng);

            Log.d("LAT & LNG GPS:", glat + " " + glng);
        }

        @Override
        public void onProviderDisabled(String provider)
        {
            Log.d("LOG", "GPS is OFF!");
        }
        @Override
        public void onProviderEnabled(String provider)
        {
            Log.d("LOG", "Thanks for enabling GPS !");
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras)
        {
        }
    }

    public void showLoc(View v) {

        //Location access ON or OFF checking
        ContentResolver contentResolver = getBaseContext().getContentResolver();
        boolean gpsStatus = Settings.Secure.isLocationProviderEnabled(contentResolver, LocationManager.GPS_PROVIDER);
        boolean networkWifiStatus = Settings.Secure.isLocationProviderEnabled(contentResolver, LocationManager.NETWORK_PROVIDER);

        //If GPS and Network location is not accessible show an alert and ask user to enable both
        if(!gpsStatus || !networkWifiStatus)
        {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(GetLocationMainActivity.this);

            alertDialog.setTitle("Make your location accessible ...");
            alertDialog.setMessage("Your Location is not accessible to us.To show location you have to enable it.");
            alertDialog.setIcon(R.drawable.warning);
            alertDialog.setNegativeButton("Enable", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    startActivityForResult(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS), 0);
                }
            });

            alertDialog.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog,int which) {
                    Toast.makeText(getApplicationContext(), "Remember to show location you have to enable it !", Toast.LENGTH_SHORT).show();
                    dialog.cancel();
                }
            });

            alertDialog.show();
        }
        //IF GPS and Network location is accessible
        else
        {
            nlocManager   = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
            nlocListener = new MyLocationListenerNetWork();
            nlocManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000 * 1, 0, nlocListener);


            glocManager  = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
            glocListener = new MyLocationListenerGPS();
            glocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000 * 1, 0, glocListener);
        }
    }
}