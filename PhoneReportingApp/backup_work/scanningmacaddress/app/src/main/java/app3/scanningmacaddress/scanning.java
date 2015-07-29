package app3.scanningmacaddress;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

/**
 * Created by 13048577 on 24/6/2015.
 */
public class scanning  {

    private Context main;
    private List<ScanResult> bestsignal;


    public void onReceive(Context arg0, Intent arg1, String TAG) {
        String bssidstr;
        int level;
        int size = 0;
        int i = 0;
        List<ScanResult> results = bestsignal;
        size = results.size();

        for (i = 0; i < size; i++) {
            bssidstr = results.get(i).BSSID;
            level = results.get(i).level;

            results = app3.scanningmacaddress.ScanResult.getScanResults();

            ScanResult bestsignal = null;
            for (ScanResult result : results) {
                if (bestsignal == null || WifiManager.compareSignalLevel(bestsignal.level, result.level) < 0)
                    bestsignal = result;

                String message = String.format("%s networks found.%s is the strongest.", results.size(), bestsignal.BSSID);

                Toast.makeText(main, message + results + bestsignal, Toast.LENGTH_SHORT).show();


                Log.d(TAG, "onReceive() message : " + message);
            }
        }
    }
}


