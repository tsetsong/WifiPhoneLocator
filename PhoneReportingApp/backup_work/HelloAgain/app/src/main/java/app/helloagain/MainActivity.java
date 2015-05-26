package app.helloagain;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import org.w3c.dom.Text;


public class MainActivity extends Activity {

    EditText message_text;
    public final static String MESSAGE_KEY = "helloagain1.message_key";

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        }

        public void sendMessage(View views)

    {


        }

    public void showGreetings(View view) {
        String button_text;
        button_text = ((Button) view).getText().toString();
        if (button_text.equals("Open Second Activity")) {
            Intent intent = new Intent(this, SecondActivity.class);
            startActivity(intent);
        } else if (button_text.equals("Open Third Activity")) {
            Intent intent = new Intent(this, ThirdActivity.class);
            startActivity(intent);
        }
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
