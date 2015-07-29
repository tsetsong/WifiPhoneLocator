package app3.scanningmacaddress;

import java.util.List;

/**
 * Created by 13048577 on 24/6/2015.
 */
public class ScanResult {
    private static List<android.net.wifi.ScanResult> scanResults;

    public static List<android.net.wifi.ScanResult> getScanResults() {
        return scanResults;
    }
}
