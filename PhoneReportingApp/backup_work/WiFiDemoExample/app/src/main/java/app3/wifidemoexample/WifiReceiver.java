package app3.wifidemoexample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;

import java.util.List;

public class WifiReceiver extends BroadcastReceiver {
    private static final String TAG = "WifiscanReceiver";

    MainActivity mainactivity;
    SmsReceiver smsReceiver;
    private int level;
    private String[] result;
    private SmsManager Manager;
    private String[] scanresults;


    public WifiReceiver(MainActivity main) {
        super();
        mainactivity = main;
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        List<ScanResult> results;
        String first;

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

        //---get the SMS message passed in---
   /*     Bundle bundle = intent.getExtras();

        SmsMessage[] msgs;
        SmsMessage[] phonenum;

        String str = "";
        String PhoneNUMBER = ""; //

        //---retrieve the SMS message received---
        Object[] pdus = (Object[]) bundle.get("pdus");

        msgs = new SmsMessage[pdus.length];
        for (i = 0; i < msgs.length; i++) {
            msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
            str += "SMS from " + msgs[i].getOriginatingAddress();
            str += " :";
            str += msgs[i].getMessageBody();
            str += "\n";
        }
         //---retrieve the SMS senders number ---
        phonenum = new SmsMessage[pdus.length];
        for (i = 0; i < phonenum.length; i++) {
            phonenum[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
            PhoneNUMBER += phonenum[i].getOriginatingAddress(); // phonenumber = phonenum  + phonenum [i].getOrignatingAddress
        }

        //---display the new SMS message---
        Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
        Toast.makeText(context, PhoneNUMBER, Toast.LENGTH_LONG).show();
        */

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

                result[x] = BSSID + "\n" + SSID + Integer.toString(level);

                result[count] = BSSID + level;
                count++;

            }
            index0 = result[0];
            System.out.println(index0);
            index1 = result[1];
            System.out.println(index1);
            index2 = result[2];
            System.out.println(index2);
            index3 = result[3];
            System.out.println(index3);
            index4 = result[4];
            System.out.println(index4);
        }

        if (count > size) {
            System.out.println("Found" + result[count]); // <-----  send message out back to the sender * Not completed *

            try {
                Bundle b = intent.getExtras();
                Object[] pdu = (Object[]) b.get("pdus");
                SmsMessage sms = null;

                for (int c = 0; c < pdu.length; c++)
                {
                    sms = SmsMessage.createFromPdu((byte[]) pdu[c]);
                }
                String sender = sms.getOriginatingAddress();
                String message = sms.getMessageBody();
                SmsManager manager = SmsManager.getDefault();
                manager.sendTextMessage(sender,"" ,index0 + index1 + index2 + index3 + index4, null,null);
                mainactivity.sendernumber = sender;

            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        System.out.println(results+"\n" + x);
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
        }
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

