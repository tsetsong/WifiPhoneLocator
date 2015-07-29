package app3.scanningmacaddress;

        import android.annotation.TargetApi;
        import android.app.Notification;
        import android.content.BroadcastReceiver;
        import android.content.Context;
        import android.content.Intent;
        import android.os.Build;
        import android.os.Bundle;
        import android.telephony.SmsManager;
        import android.telephony.SmsMessage;
        import android.util.Log;
        import android.widget.Toast;


class SMSResponder extends  BroadcastReceiver {

    private Notification.Action.Builder intent;
    private Context context;

    @TargetApi(Build.VERSION_CODES.KITKAT_WATCH)
    @Override
    public void onReceive(Context arg0, Intent arg1) {
        // creating the sms receiver
        try {
            Bundle b = arg1.getExtras();
            Object[] pdu = (Object[]) b.get("pdus");
            SmsMessage sms = null;
            for (int i = 0; i < pdu.length; i++) {
                sms = SmsMessage.createFromPdu((byte[]) pdu[i]);
            }

            String sender = sms.getOriginatingAddress();
            String message = sms.getMessageBody();
            Toast.makeText(arg0, "Sender :"  + "message:" + message, Toast.LENGTH_LONG).show();

            // SMS reply
            if ( message.contains ("Locate"))
            {
                SmsManager manager = SmsManager.getDefault();
                manager.sendTextMessage(sender, "", " Auto-Reply Message Sorry :) ", null, null);
            }

            final Bundle bundle = intent.getExtras();

            if (bundle != null) {

                final Object[] pdusObj = (Object[]) bundle.get("pdus");

                for (int i = 0; i < pdusObj.length; i++) {

                    SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                    String phoneNumber = currentMessage.getDisplayOriginatingAddress();

                    String senderNum = phoneNumber;
                    message = currentMessage.getDisplayMessageBody();

                    Log.i("SmsReceiver", "senderNum: " + senderNum + "; message: " + message);

                    // Show alert
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, "senderNum: " + senderNum + ", message: " + message, duration);
                    toast.show();

                } // end for loop
            } // bundle is null


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}