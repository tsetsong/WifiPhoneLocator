package com.example.nurul.myapplication5;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.BreakIterator;
import java.util.List;


public class MainActivity5 extends ActionBarActivity implements View.OnClickListener {
    private static final String TAG="WiFiDemo";
    WifiManager wifi;
    BroadcastReceiver receiver;
    TextView text;
    Button btn;
    private BreakIterator macAddress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity5);
        text = (TextView) findViewById(R.id.text);
        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(this);
        //get wifi status
        wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifi.getConnectionInfo();
        text.append("\n\nwifi status :" + info.toString());

        //WifiManager manager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        //WifiInfo info = manager.getConnectionInfo();
        //string address = info.getMacAddress();

        //list available network

        List<WifiConfiguration> configurations = wifi.getConfiguredNetworks();
        for (WifiConfiguration configuration : configurations) ;
        {
            text.append("\n\n" + configurations.toString());
        }
        //register broadcast receiver
        if (receiver == null)
            receiver = new WifiScaner(this);
        registerReceiver(receiver, new IntentFilter(
                WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        Log.d(TAG, "onCreate()");
        }

    //@Override
    protected void onStop() {
        // TODO Auto-generated method stub
        unregisterReceiver(receiver);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);

    }




    @Override
    public void onClick(View arg0) {
        //TODO Auto-generated method stub
        Toast.makeText(getApplicationContext(), "All Network searched!!",0).show();
        if (view.getId()==R.id.btn){
            Log.d(TAG, "onCreate() wifi.startScan()");
            wifi.startScan();
        }
        WifiManager wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        WifiInfo wInfo = wifiManager.getConnectionInfo();
        String macAddress2 = wInfo.getMacAddress();


        macAddress.setText(macAddress2);
    }
}


