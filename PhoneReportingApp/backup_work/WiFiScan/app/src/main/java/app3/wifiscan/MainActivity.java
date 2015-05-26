package com.android.wifitester;

import java.util.List;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class WifiTester extends Activity {

    <uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
    <uses-permission android:name="android.permission.CHANGE_WIFI_STATE" />
        IntentFilter filter = new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
    registerReceiver(wifiReciever, filter);

    WifiManager wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
    if (wifiManager == null) {
        // Device does not support Wi-Fi
    } else {
        // Any vaild Wi-Fi operations
    }

    wifiManager.setWifiEnabled(true); // To turn on the Wi-Fi

    wifiManager.startScan();

    class WiFiScanReceiver extends BroadcastReceiver {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (WifiManager.SCAN_RESULTS_AVAILABLE_ACTION.equals(action)) {
                List<ScanResult> wifiScanResultList = wifiManager.getScanResults();
                for(int i = 0; i < wifiScanResultList.size(); i++){
                    String hotspot = (wifiScanResultList.get(i)).toString();
                    adapter.add(hotspot);
                }
            }
        }
    }

    public void getWifiActivity(View view) {
        Intent intent = new Intent(getApplicationContext(), WifiTester.class);
        startActivity(intent);
    }