package app2.checkboxes;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;


public class CheckBoxes extends Activity {
    ArrayList<String> selection=new ArrayList<String>();
    TextView final_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check_boxes);
        final_text = (TextView) findViewById(R.id.final_result);
        final_text.setEnabled(false);


    }

    public void selectItem(View view)
    {
       boolean checked = ((CheckBox) view).isChecked();
        switch (view.getId())
        {
         case R.id.fruit_apple:
                        if(checked)
                        {  selection.add("Apple"); }
          else
              {
               selection.remove("Apple");
              }
              break;


            case R.id.fruit_orange:
            if (checked)
            {selection.add("Orange");}
            else {
                selection.remove("Orange");
            }
            break;


            case R.id.fruit_grapes:
                if(checked)
                { selection.add("Grapes");}
                else
                {
                    selection.remove("Grapes");
                }
                break;






        }





    }

    public void finalSelection(View view)
    {
     String final_fruit_selection = "";

        for(String Selections : selection)
        {
            final_fruit_selection = final_fruit_selection + Selections + "\n";
        }

        final_text.setText(final_fruit_selection);
            final_text.setEnabled(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_check_boxes, menu);
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
