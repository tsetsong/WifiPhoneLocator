package app3.wifiscanreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.widget.Toast;

import java.util.List;

public class WiFiScanReceiver extends BroadcastReceiver {
    WiFiScanReceiver wifiDemo;
    private WifiManager wifi;

    public WiFiScanReceiver(BroadcastReceiver wifiDemo) {
        super();
        this.wifiDemo = (WiFiScanReceiver) wifiDemo;
    }

    public WiFiScanReceiver(WiFiscanReceiver wiFiscanReceiver) {

    }

    @Override
    public void onReceive(Context c, Intent intent) {
        List<ScanResult> results = wifiDemo.wifi.getScanResults();
        ScanResult bestSignal = null;
        for (ScanResult result : results) {
            if (bestSignal == null
                    || WifiManager.compareSignalLevel(bestSignal.level, result.level) < 0)
                bestSignal = result;
        }

        assert bestSignal != null;
        String message = String.format("%s networks found. %s is the strongest.",
                results.size(), bestSignal.SSID);



    }

    private Toast makeText(WiFiScanReceiver wifiDemo, String message, int lengthLong) {
        return null;
    }

}