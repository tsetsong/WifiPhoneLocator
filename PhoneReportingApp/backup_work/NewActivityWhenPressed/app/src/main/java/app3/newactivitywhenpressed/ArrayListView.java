package app3.newactivitywhenpressed;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class ArrayListView extends ActionBarActivity {

    //Array for ListView
    ListView listView;
    ArrayAdapter<String> Adapter;
    String[] android_versions = {
            "Message1",
            "Message2",
            "Message3",
            "Message4",
            "Message5"
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_array_list_view);

        ListView lvMsg = (ListView) findViewById(R.id.lvMsg);
        Adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,android_versions);
        lvMsg.setAdapter(Adapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_array_list_view, menu);
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
