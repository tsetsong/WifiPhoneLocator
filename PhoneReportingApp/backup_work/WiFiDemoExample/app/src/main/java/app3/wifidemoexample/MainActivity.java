package app3.wifidemoexample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {


    private static final String TAG = "WiFiDemo";
    WifiManager wifi;
    BroadcastReceiver receiver;
    TextView text;
    Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (TextView) findViewById(R.id.text);
        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(this);
        //get wifi status

        wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        List<ScanResult> wifiScanList;
        wifiScanList = wifi.getScanResults();

        // list available network


        List<WifiConfiguration> configurations = wifi.getConfiguredNetworks();
       for (WifiConfiguration configuration : configurations) {
          text.append("\n\n" + configuration.toString());

        } // register broadcast receiver
        if (receiver==null)
             receiver=new WifiScanner(this);
        registerReceiver(receiver, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        Log.d(TAG,"onCreate()");
    }

    @Override
    protected void onStop() {
        unregisterReceiver(receiver);
        super.onStop();
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
    @SuppressWarnings("ResourceType")
    @Override
    public void onClick(View view) {
        //noinspection ResourceType,ResourceType,ResourceType,ResourceType
        Toast.makeText(getApplicationContext(), "All Network searched !!", 0).show();
        if(view.getId()==R.id.btn){
            Log.d(TAG,"onCreate() wifi startScan()");
            wifi.startScan();
        }
    }
}
