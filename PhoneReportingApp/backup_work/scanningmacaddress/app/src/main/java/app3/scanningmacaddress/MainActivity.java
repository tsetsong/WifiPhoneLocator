package app3.scanningmacaddress;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.*;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MainActivity extends ActionBarActivity {
    WifiManager wifi;
    ListView lv;
    TextView textStatus;
    Button buttonScan;
    Switch enable;
    int size = 0;
    List<android.net.wifi.ScanResult> results;

    String ITEM_KEY = "key";
    ArrayList<HashMap<String, String>> arraylist = new ArrayList<HashMap<String, String>>();
    SimpleAdapter adapter;
    private TextView text;

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    public MainActivity(Bundle savedInstanceState) {


        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            buttonScan.setOnClickListener((View.OnClickListener) this);
            text = (TextView) findViewById(R.id.simple_list_item_1);
            wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
            enable.setChecked(wifi.isWifiEnabled());
            enable.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton compoundbutton, boolean flag) {
                    wifi.setWifiEnabled(flag);
                    if (flag) {
                        arraylist.clear();
                        lv.setVisibility(View.VISIBLE);
                        buttonScan.setEnabled(true);
                        wifi.startScan();
                    } else {
                        lv.setVisibility(View.INVISIBLE);
                        buttonScan.setEnabled(false);
                    }
                }
            });
            lv.setAdapter(this.adapter);
            lv.setOnClickListener(new View.OnClickListener() {

                public void onClick(View view) {
                    int a = lv.getSelectedItemPosition();

                }
            });

            registerReceiver(new BroadcastReceiver() {
                @Override
                public void onReceive(Context c, Intent intent) {
                    results = wifi.getScanResults();
                    size = results.size();
                    for (int i = size - 1; i >= 0; i--) {
                        HashMap<String, String> item = new HashMap<String, String>();
                        item.put(ITEM_KEY, results.get(i).SSID);
                        arraylist.add(item);
                    }
                    if (size > 0)
                        adapter.notifyDataSetChanged();
                }
            }, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        }
    }
    public void onClick(View view)
    {
        arraylist.clear();
        wifi.startScan();
        Toast.makeText(this, "Scanning....", Toast.LENGTH_SHORT).show();

    }
}


