package com.myapplication5example.mainactivity5;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity implements OnClickListener {

    public static final String TAG = "WiFiDemo";

    WifiManager mainWifiObj;
    BroadcastReceiver receiver;
    TextView textview;
    Button btn;
    ListView listview;

    @Override

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        textview = (TextView) findViewById(R.id.text);
        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(this);

// get wifi status

        listview=(ListView)findViewById(R.id.listView);
        mainWifiObj = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        if (mainWifiObj.isWifiEnabled() == false)

        {

            Toast.makeText(getApplicationContext(), "wifi is disabled..making it enabled", Toast.LENGTH_LONG).show();
            mainWifiObj.setWifiEnabled(true);

        }

// register broadcast receiver

        if (receiver == null)
            receiver = new wifiScanReceiver(this);
        registerReceiver(receiver, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        Log.d(TAG, "onCreate()");

    }

    @Override

    protected void onStop() {

// TODO Auto-generated method stub

        unregisterReceiver(receiver);
        super.onStop();

    }

//@Override

    public void onClick(View view){

// TODO Auto-generated method stub

        Toast.makeText(getApplicationContext(), "All Network Searched !!", Toast.LENGTH_SHORT).show();

        if(view.getId()==R.id.btn){
            Log.d(TAG, "onCreate() wifi.startScan()");
            mainWifiObj.startScan();

        }

    }

    @Override

    public boolean onOptionsItemSelected(MenuItem item) {

// Handle action bar item clicks here. The action bar will
// automatically handle clicks on the Home/Up button, so long
// as you specify a parent activity in AndroidManifest.xml
        int id = item.getItemId();
//noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}