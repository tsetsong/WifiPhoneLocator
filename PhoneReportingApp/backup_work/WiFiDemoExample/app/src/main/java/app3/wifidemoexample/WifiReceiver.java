package app3.wifidemoexample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.telephony.SmsManager;
import java.util.List;

public class WifiReceiver extends BroadcastReceiver  {
    private static final String TAG = "WifiscanReceiver";

    MainActivity mainactivity;
    SmsReceiver smsReceiver;
    private int level;
    private String[] result;
    private SmsManager Manager;
    private String[] scanresults;

    public WifiReceiver(MainActivity main)
    {
        super();
        mainactivity = main;
    }
    @Override
    public void onReceive(Context arg0, Intent intent) {

        List<ScanResult> results;
        String first;
        String GpsStatus;
        String BSSID = null;
        result = new String[0];
        String[] getresults = null;
        String SSID = null;
        int level = 0;
        int size = 0;
        int i;
        int x = 0;
        result = getresults;
        String index0, index1, index2, index3, index4;
        int count = 0;

        String a = String.valueOf(x);

        results = mainactivity.wifi.getScanResults();
        if (results.size() > 5) {
            size = 5;
        } else

        {
            size = results.size();
        }

        result = new String[results.size()];

        {
            for (x = 0; x < 5; x++) // x less than 5 until it makes x = 4
            {
                BSSID = results.get(x).BSSID;
                level = results.get(x).level;
                SSID = results.get(x).SSID;

                result[x] = BSSID + "\n" + "\n" + SSID + "\n" + Integer.toString(level);

                result[count] = BSSID + "\n" +level;
                count++;

            }
            index0 = result[0];

            index1 = result[1];

            index2 = result[2];

            index3 = result[3];

            index4 = result[4];
        }

        String message = null;

        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(mainactivity.sendernumber, null, result[0]+ "\n" + result[1]+ "\n" +result[2]+ "\n" +result[3]+ "\n" +result[4] , null, null);


        /*
            System.out.println("Found" + result[count]); // <-----  send message out back to the sender * Not completed *

            final Bundle bundle = intent.getExtras();
            try {
                if (bundle != null) {
                    final Object[] pdusObj = (Object[]) bundle.get("pdus");

                    for (i = 0; i < pdusObj.length; i++) {
                        SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                        String phoneNumber = currentMessage.getDisplayOriginatingAddress();

                        String senderNum = phoneNumber;
                        message = currentMessage.getDisplayMessageBody();

                        Log.i("SmsReceiver", "senderNum: " + senderNum + "; message: " + message);

                        // Show alert
                        int duration = Toast.LENGTH_LONG;

                        Context context = null;
                        Toast toast = Toast.makeText(context, index0 + index1 + index2 + index3 + index4 + senderNum + message, duration);
                        toast.show();

                    } // end for loop
                } // bundle is null

            } catch (Exception e) {
                Log.e("SmsReceiver", "Exception smsReceiver" + e);

            }
        */
        }
    }

//          if (size > 5)  // Size more than 5
//         {
  /*          for (x = 0; x < 5; x++) {
                first = result[x];
                System.out.println(first);
           }
            if (count >= 5) //count less than or equal to 5
           {

           }
*/

    //mainactivity.listview.setAdapter(new ArrayAdapter<>(context.getApplicationContext(), android.R.layout.simple_list_item_1, result));

  //      sendreplyMessage(sendreplyMessage());



/*    public String sendreplyMessage()
    {
        return null;
    }

    public String sendreplyMessage(String sender)
    {
        Log.d("phone log ", sender);

        if (result != (null))

        {
            Manager = SmsManager.getDefault();
            System.out.println("phone no is" + sender);
            int count = 0;
            Manager.sendTextMessage(sender, "", result[count], null, null);
        }
        return null;
    }
*/


 //       new sendTask().execute();

/*  class sendTask extends AsyncTask<String, Void, String> {
        protected String doInBackground(String... params){

            try{
                Socket socket = new Socket("172.20.10.11",8888);
                PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
                out.println(result[0]+level);
                out.close();
                socket.close();
            }
            catch(Exception e){
                System.out.println("Error"+ e);
            }
            try
            {
                Thread.sleep(1000);
            }catch(Exception e){
                Thread.currentThread().interrupt();
            }
            try
            {
                Socket socket = new Socket("172.20.10.11",8888);
                PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
                out.println(result[1]+level);
                out.close();
                socket.close();
            }
            catch(Exception e){
                System.out.println("Error"+ e);
            }
            try
            {
                Thread.sleep(1000);
            }catch(Exception e){
                Thread.currentThread().interrupt();
            }
            try
            {
                Socket socket = new Socket("172.20.10.11",8888);
                PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
                out.println(result[2]+level);
                out.close();
                socket.close();
            }
            catch(Exception e){
                System.out.println("Error"+ e);
            }
            try{
                Thread.sleep(1000);
            }catch(Exception e){
                Thread.currentThread().interrupt();
            }
            try
            {
                Socket socket = new Socket("172.20.10.11",8888);
                PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
                out.println(result[3]+level);
                out.close();
                socket.close();
            }
            catch(Exception e)
            {
                System.out.println("Error"+ e);
            }

            try
            {
                Thread.sleep(1000);
            }catch(Exception e){
                Thread.currentThread().interrupt();
            }

            try{
                Socket socket = new Socket("172.20.10.11",8888);
                PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
                out.println(result[4]+level);
                out.close();
                socket.close();
            }
            catch(Exception e){
                System.out.println("Error"+ e);
            }
            return null;
        }

        protected void onProgressUpdate(Integer... progress){

        }
        protected void onPostExecute(Long result){

        }

*/

