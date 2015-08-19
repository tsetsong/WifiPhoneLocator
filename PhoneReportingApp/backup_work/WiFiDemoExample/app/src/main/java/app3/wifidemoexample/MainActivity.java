package app3.wifidemoexample;

/**
 * Created by 13048577 on 2/7/2015.
 */


import android.content.Context;
import android.content.IntentFilter;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity  {

    private static final String TAG = "WiFiDemo";
    public WifiManager wifi;
    WifiReceiver wifireceiver;
    SmsReceiver smsreceiver;
    ListView listview;
    //EditText editText;
    private WifiInfo info;
    private int ArrayOfUsers;
    public String sendernumber;
    TextView txtV;


    //    TextView text;
    //    ArrayAdapter<String> adapter
/*
class loginRunnable implements Runnable {
        public void run() {
            String fromServer = null;
            while (true) {
                try {
                    ServerSocket serverSocket = new ServerSocket(8888);
                    Socket clientSocket = serverSocket.accept();
                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    fromServer = in.readLine();
                    data +="//"+ fromServer;

                    in.close();
                    clientSocket.close();
                    serverSocket.close();
                } catch (Exception e) {
                }
            }
        }
*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        //editText = (EditText) findViewById(R.id.editText);
        listview = (ListView) findViewById(R.id.listView);
        txtV = (TextView) findViewById(R.id.textview1);
        wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        if (!wifi.isWifiEnabled()) {
            Toast.makeText(getApplicationContext(), "wifi is disabled..making it enabled", Toast.LENGTH_LONG).show();
            wifi.setWifiEnabled(true);
        }

        // List<WifiConfiguration> configurations = wifi.getConfiguredNetworks();
        if (wifireceiver == null)
            wifireceiver = new WifiReceiver(this);
        if (smsreceiver == null)
            smsreceiver = new SmsReceiver(this);


        registerReceiver(wifireceiver, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        registerReceiver(smsreceiver, new IntentFilter("android.provider.Telephony.SMS_RECEIVED"));
        Log.d(TAG, "onCreate()");


//        IntentFilter filter = new IntentFilter(android.provider.Telephony.SMS_RECEIVED);
//        this.registerReceiver(smsreceiver, filter);
/*
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getBaseContext(), parent.getItemAtPosition(position) + " is selected", Toast.LENGTH_LONG).show();

                //get wifi status}
                // list available network
                List<WifiConfiguration> configurations = wifi.getConfiguredNetworks();
                for (WifiConfiguration configuration : configurations) {
                    text.append("\n\n" + configuration.toString());

                } // register broadcast receiver
                if (receiver == null)
                    receiver = new Receiver(this);
                registerReceiver(receiver, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
                Log.d(TAG, "onCreate()");
            }
        });text = (TextView) findViewById(R.id.text);

        ListView listView = (ListView) findViewById(R.id.list_view);
        adapter = new ArrayAdapter<String>(this,R.layout.list_view_custom_layout,R.id.list_item,android_version);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
*/
    }

    protected void onPause() {
        unregisterReceiver(wifireceiver);
        unregisterReceiver(smsreceiver);
        super.onPause();
    }

    protected void onResume() {
        registerReceiver(wifireceiver, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        super.onResume();
    }


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