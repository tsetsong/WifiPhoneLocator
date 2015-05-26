package app3.newactivitywhenpressed;

    import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;


public class MainActivity2Activity extends Activity implements OnClickListener {

        public void buttonOnClick1(View v){
            Button button1 = (Button) v;
            startActivity(new Intent(getApplicationContext(),ArrayListView.class));

        }


        // GUI Widget
        Button btnSent, btnInbox, btnDraft;
        TextView lblMsg , lblNo;
        ListView lvMsg;



        // Cursor Adapter
        SimpleCursorAdapter adapter;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main_activity2);






            //Init GUI Widget
            btnInbox = (Button) findViewById(R.id.btnInbox);
            btnInbox.setOnClickListener(this);

            btnSent = (Button) findViewById(R.id.btnSentBox);
            btnSent.setOnClickListener(this);

            btnDraft = (Button) findViewById(R.id.btnDraft);
            btnDraft.setOnClickListener(this);

            lvMsg = (ListView) findViewById(R.id.lvMsg);
        }

        @Override
        public void onClick(View v) {

            if (v == btnInbox) {
                // Create Inbox box URI
                Uri inboxURI = Uri.parse("content://sms/inbox");

                //List Required columns

                String[] reqCols = new String[]{"_id", "address", "body"};

                        //Get Content Resolver object, which will deal with Content provider
                        ContentResolver cr = getContentResolver();

                //Fetch Inbox SMS message from built-in Content Provider

                Cursor c = cr.query(inboxURI, reqCols, null, null, null);

                //Attached Cursor with adapter and display in listview
                adapter = new SimpleCursorAdapter(this, R.layout.row,c,
                         new String[]{ "body", "address"}, new int[] {
                                R.id.lblMsg,R.id.lblNumber });
                lvMsg.setAdapter(adapter);
                }

                if (v == btnSent) {
                    //Create Sent box URI
                    Uri sentURI = Uri.parse("content://sms/sent");

                    // List required columns
                    String[] reqCols = new String[]{"_id", "address", "body"};

                    //Get content resolver object, which will deal with content provider
                    ContentResolver cr = getContentResolver();

                    //Fetch Sent SMS Message from built-in Content Provider
                    Cursor c = cr.query(sentURI, reqCols, null, null, null);

                    //Attached Cursor with adapter and display in listview
                    adapter = new SimpleCursorAdapter(this, R.layout.row, c,
                            new String[]{"body", "address"}, new int[]{
                            R.id.lblMsg, R.id.lblNumber});
                    lvMsg.setAdapter(adapter);
                }

                if (v == btnDraft) {
                    //Create Sent box URI
                    Uri draftURI = Uri.parse("content://sms/draft");

                    // List required columns
                    String[] reqCols = new String[]{"_id", "address", "body"};

                            //Get content resolver object, which will deal with content provider
                            ContentResolver cr = getContentResolver();
                    //Fetch Sencr.query(draftURI, reqCols, null, null, null);
                    Cursor c = cr.query(draftURI, reqCols, null, null, null);
                    //Attached Cursor with adapter and display in listview
                    adapter = new SimpleCursorAdapter(this, R.layout.row, c,
                            new String[]{"body", "address"}, new int[]{
                            R.id.lblMsg, R.id.lblNumber});
                    lvMsg.setAdapter(adapter);

                    }
                }
            }
