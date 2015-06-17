package app3.wifidemoexample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

/**
 * Created by 13048577 on 8/5/2015.
 */
public class WifiScanner extends BroadcastReceiver {
    private static final String TAG = "WifiscanReceiver";
    MainActivity main;

    public WifiScanner(MainActivity main) {
        super();
        this.main = main;
    }

    @Override
    public void onReceive(Context arg0, Intent arg1) {
        List<android.net.wifi.ScanResult> results = main.wifi.getScanResults();
        android.net.wifi.ScanResult bestsignal = null;
        for (android.net.wifi.ScanResult result : results) {
            if (bestsignal == null || WifiManager.compareSignalLevel(bestsignal.level, result.level)<0)
                bestsignal = result;

        }

        String message = String.format("%s networks found.%s is the strongest.", results.size(), bestsignal.BSSID);
        //noinspection ResourceType
        Toast.makeText(main, message, 0).show();

        Log.d(TAG, "onReceive() message : " + message);
    }
}