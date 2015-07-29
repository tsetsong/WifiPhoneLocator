package com.myapplication5example.mainactivity5;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.widget.ArrayAdapter;
import java.util.List;

/**
 * Created by Nurul on 28/5/2015.
 */

public class wifiScanReceiver extends BroadcastReceiver {

    private static final String TAG = "wifiScanReceiver";

    MainActivity main;

    public wifiScanReceiver(MainActivity main) {
        super();
        this.main = main;
    }
    @Override
    public void onReceive(Context context, Intent intent)
    {
        List<ScanResult> results;
        String BSSID;
        String wifisstr_array[];
        int level;
        int size = 0;
        int i = 0;

        results = main.mainWifiObj.getScanResults();
        size = results.size();
        wifisstr_array = new String[results.size()];
        for (i = 0; i < size; i++)

        {
            BSSID = results.get(i).BSSID;
            level = results.get(i).level;
            wifisstr_array[i] = BSSID +"\n"+Integer.toString(level);

        }

        main.listview.setAdapter(new ArrayAdapter<String>(context.getApplicationContext(),android.R.layout.simple_list_item_1,wifisstr_array));

        }

        }