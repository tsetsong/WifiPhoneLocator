package backup.backupwifi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;


public class wifi  {
    TextView textConnected, textIp, textSsid, textBssid, textMac, textSpeed, textRssi;
    private static List<ScanResult> scanResults;
    private WifiManager mWifiManager;
    private int contentView;
    private int Connected;
    private int Wifi;
    private int ssid;
    private int bssid;
    private int Mac;
    private android.net.ConnectivityManager myConnManager;
    private wifi mContext;


    public static List<ScanResult> getScanResults() {
        return scanResults;
    }


    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.activity_wifi);
        textConnected = (TextView) textConnected.findViewById(Connected);
        textIp = (TextView) textIp.findViewById(Wifi);
        textSsid = (TextView) textSsid.findViewById(ssid);
        textBssid = (TextView) textBssid.findViewById(bssid);
        textMac = (TextView) textMac.findViewById(Mac);


        DisplayWifiState();

        this.registerReceiver(this.myWifiReceiver,
                new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));

    }

    private void registerReceiver(BroadcastReceiver myWifiReceiver, IntentFilter intentFilter) {

    }

    private BroadcastReceiver myWifiReceiver
            = new BroadcastReceiver(){

        @Override
        public void onReceive(Context arg0, Intent arg1) {
            // TODO Auto-generated method stub
            NetworkInfo networkInfo = (NetworkInfo) arg1.getParcelableExtra(ConnectivityManager.EXTRA_NETWORK_INFO);
            if(networkInfo.getType() == ConnectivityManager.TYPE_WIFI){
                DisplayWifiState();
            }
    }};

public void DisplayWifiState(){


        NetworkInfo myNetworkInfo = myConnManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        WifiManager myWifiManager =  (WifiManager) mContext.getSystemService(Context.WIFI_SERVICE);
        WifiInfo myWifiInfo = myWifiManager.getConnectionInfo();


        textMac.setText(myWifiInfo.getMacAddress());

        if (myNetworkInfo.isConnected()){
            int myIp = myWifiInfo.getIpAddress();

            textConnected.setText("--- CONNECTED ---");

            int intMyIp3 = myIp/0x1000000;
            int intMyIp3mod = myIp%0x1000000;

            int intMyIp2 = intMyIp3mod/0x10000;
            int intMyIp2mod = intMyIp3mod%0x10000;

            int intMyIp1 = intMyIp2mod/0x100;
            int intMyIp0 = intMyIp2mod%0x100;

            textIp.setText(String.valueOf(intMyIp0)
                            + "." + String.valueOf(intMyIp1)
                            + "." + String.valueOf(intMyIp2)
                            + "." + String.valueOf(intMyIp3)
            );

            textSsid.setText(myWifiInfo.getSSID());
            textBssid.setText(myWifiInfo.getBSSID());

            textSpeed.setText(String.valueOf(myWifiInfo.getLinkSpeed()) + " " + WifiInfo.LINK_SPEED_UNITS);
            textRssi.setText(String.valueOf(myWifiInfo.getRssi()));
        }
        else{
            textConnected.setText("--- DIS-CONNECTED! ---");
            textIp.setText("---");
            textSsid.setText("---");
            textBssid.setText("---");
            textSpeed.setText("---");
            textRssi.setText("---");
        }

    }

    private void getSystemService(String wifiService) {

    }


    public void onReceive(Context context, Intent intent) {

    }

    public void setContentView(int contentView) {
        this.contentView = contentView;
    }
}
