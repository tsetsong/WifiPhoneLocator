package com.example.nurul.myapplication5;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.util.Log;
import android.widget.Toast;

import java.util.List;
import java.util.Scanner;

/**
 * Created by Nurul on 8/5/2015.
 */
public class WifiScaner extends BroadcastReceiver {

    private static final String TAG="WifiscanReceiver";
    MainActivity5 main;

    public WifiScaner(MainActivity5 main){
        super();
        this.main=main;
    }
    @Override
    public void onReceive(Context arg0, Intent arg1) {
        //TODO Auto-generated method stub
        List<ScanResult> results=main.wifi.getScanResults();
        ScanResult bestsignal =null;
        for (ScanResult result:results){
            if(bestsignal==null || WifiManager.compareSignalLevel(bestsignal.level, result.level)<0)
                bestsignal=result;
        }
    String message=String.format("%s networks found.%s is the strongest.",results.size(),bestsignal.SSID);
        Toast.makeText(main, message, 0).show();
        Log.d(TAG, "onReceiver() message :"+message);
    }
 }
