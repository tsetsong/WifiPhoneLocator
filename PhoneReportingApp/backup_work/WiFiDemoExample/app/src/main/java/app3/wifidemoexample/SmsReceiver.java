package app3.wifidemoexample;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.widget.Toast;


public class SmsReceiver extends BroadcastReceiver
{
    private static final String TAG = "SmsReceiver";
    MainActivity mainactivity;
    SmsReceiver smsReceiver;
    public SmsReceiver(MainActivity main)
    {
        super();
        mainactivity = main;
    }
    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public void onReceive(Context arg0, Intent intent)
    {
        try
        {
                Bundle b = intent.getExtras();
                Object[] pdu = (Object[]) b.get("pdus");
                SmsMessage sms = null;
                for (int i = 0; i < pdu.length; i++)
                {
                    sms = SmsMessage.createFromPdu((byte[]) pdu[i]);
                }

                String sender = sms.getOriginatingAddress();
                String message = sms.getMessageBody();
                Toast.makeText(arg0, "" + sender + message, Toast.LENGTH_LONG).show();
                // SMS reply
                SmsManager manager = null;
            if (message.contains("Locate123"))

            {
                manager = SmsManager.getDefault();
                manager.sendTextMessage(sender, "", "msg received, locating ...", null, null);
                mainactivity.sendernumber = sender;
                mainactivity.wifi.startScan();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}

