package app3.wifihelper;

import android.content.Context;
import android.net.wifi.WifiManager;

import java.util.List;

/**
 * Created by 13048577 on 17/6/2015.
 */
public class BroadcastReceiver
{
    private final Object i;
    private BroadcastReceiver receiver;

    receiver = new BroadcastReceiver(){

        public void onReceive(Context Context c;
    c, Intent i){

            WifiManager w = (WifiManager) c.getSystemService(Context.WIFI_SERVICE);

            List l = w.getScanResults();

            StringBuilder wn = new StringBuilder("Scan Results:\n");

            wn.append("-------------\n");

            for (ScanResult r : l) {

                wn.append(r.SSID + " " + r.level + " dBM\n");

            }

        }
    i = null;
};
}
