package app3.refreshwifiinfo;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.preference.Preference;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends ActionBarActivity {

    private WifiManager mWifiManager
            ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        private void refreshWifiInfo() {
            final Context context = getActivity();
            WifiInfo wifiInfo = mWifiManager.getConnectionInfo();

            Preference wifiMacAddressPref = findPreference(KEY_MAC_ADDRESS);
            String macAddress = wifiInfo == null ? null : wifiInfo.getMacAddress();
            wifiMacAddressPref.setSummary(!TextUtils.isEmpty(macAddress) ? macAddress
                    : context.getString(R.string.status_unavailable));
            wifiMacAddressPref.setSelectable(false);

            Preference wifiIpAddressPref = findPreference(KEY_CURRENT_IP_ADDRESS);
            String ipAddress = Utils.getWifiIpAddresses(context);
            wifiIpAddressPref.setSummary(ipAddress == null ?
                    context.getString(R.string.status_unavailable) : ipAddress);
            wifiIpAddressPref.setSelectable(false);
        }
    }

    private Context getActivity() {
        return null;
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
