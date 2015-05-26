package app3.toggledemo;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;


public class MainActivity extends ActionBarActivity {
        TextView textView;
        Switch aSwitch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView =(TextView) findViewById(R.id.result_text);
        textView.setVisibility(View.INVISIBLE);
        aSwitch = (Switch)findViewById(R.id.toggle_button_2);
        aSwitch .setOnCheckedChangeListener (new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    textView.setText("Wifi On");
                    textView.setVisibility(View.VISIBLE);
                }
                else
                {
                    textView.setText("Wifi Off");
                }
            }
        });
    }
    public void changeVibrateState(View view)
    {
     boolean checked = ((ToggleButton)view).isChecked();
        if(checked)
        {
            textView.setText("Vibrate On");
            textView.setVisibility(View.VISIBLE);
        }
        else
        {
            textView.setText("Vibrate off");

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
