package scanresult.scanresult;

import android.app.ListActivity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;


public class MainActivity extends ListActivity {

    @Override public void

        onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        final WifiManager manager=(WifiManager) getSystemService(WIFI_SERVICE);
        IntentFilter filter = new IntentFilter();
        filter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
        registerReceiver(new BroadcastReceiver() {
            private ArrayAdapter<String> listAdapter;

            public void setListAdapter(ArrayAdapter<String> results) {
                this.listAdapter = results;
                String[] values = new String[]{"", "", "",};
               ArrayAdapter<String> setListadapter = new ArrayAdapter<String>(this,
                       R.layout.list_entry_layout, R.id.list_view_custom_layout, values);
                setListAdapter(setListadapter);
                ListView list = (ListView) findViewById(R.id.listView);
                list.setAdapter(new YourAdapter());
            }

            @Override
            public void onReceive(Context context, Intent arg1) {

                List<ScanResult> results = manager.getScanResults();
                final String[] items = new String[results.size()];
                for (int i = 0; i < results.size(); ++i) {
                    items[i] = results.get(i).SSID;

                }

                Toast.makeText(context, "receive wifi scan results", Toast.LENGTH_SHORT).show();
                final ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, items);
                setListAdapter(adapter);
            }
        }
                , filter);
        if (manager.getWifiState() == WifiManager.WIFI_STATE_ENABLED) {
            manager.startScan();
        }
    }


    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        String item = (String) getListAdapter().getItem(position);
        Toast.makeText(this, item + " selected", Toast.LENGTH_LONG).show();
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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
}
