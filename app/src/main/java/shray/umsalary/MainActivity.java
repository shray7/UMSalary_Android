package shray.umsalary;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends Activity implements OnItemSelectedListener {
    private int yearOption;
    private int campusOption;

    Map<String, Integer> yearMap = new HashMap<String, Integer>();
    Map<String, Integer> campusMap = new HashMap<String, Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv = (TextView) findViewById(R.id.UMTopHeader);
        Spinner yearspinner = (Spinner) findViewById(R.id.YearSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.year_array, android.R.layout.simple_spinner_item);
        Spinner campusspinner = (Spinner) findViewById(R.id.CampusSpinner);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.campus_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yearspinner.setAdapter(adapter);
        yearspinner.setOnItemSelectedListener(this);
        campusspinner.setAdapter(adapter2);
        campusspinner.setOnItemSelectedListener(this);

        tv.setKeyListener(null);
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position,long id) {

        switch (parent.getId()){
            case R.id.YearSpinner: {
                String year = parent.getItemAtPosition(position).toString();
                yearOption = GetYearOptionValue(year);
                break;
            }
            case R.id.CampusSpinner: {
                String campus = parent.getItemAtPosition(position).toString();
                campusOption = GetCampusOptionValue(campus);
                break;
            }
        }
    }
    public void onNothingSelected(AdapterView<?> parent) {}

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

    public void GetData(View view) {
        Intent intent = new Intent(this, GetSalary.class);
        EditText editTextFirstName = (EditText) findViewById(R.id.firstname);
        EditText editTextLastName = (EditText) findViewById(R.id.lastname);
        String fn = editTextFirstName.getText().toString().trim();
        String ln = editTextLastName.getText().toString().trim();

        if(fn.isEmpty() && ln.isEmpty())
        {
            Toast toast = Toast.makeText(getApplicationContext(),"Please Enter First Or Last Name", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
        else if(ln.isEmpty())
        {
            Toast toast = Toast.makeText(getApplicationContext(),"Last Name is required", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
        else
        {
            intent.putExtra("fn", fn);
            intent.putExtra("ln", ln);
            intent.putExtra("year", this.yearOption);
            intent.putExtra("campus", this.campusOption);

            startActivity(intent);
        }
    }

    private int GetYearOptionValue(String year)
    {
        for(int i=0;i<13;i++)
        {
            yearMap.put(getResources().getStringArray(R.array.year_array)[i],i);
        }
        return yearMap.get(year);
    }

    private int GetCampusOptionValue(String campus)
    {
        for(int i=0;i<4;i++)
        {
            campusMap.put(getResources().getStringArray(R.array.campus_array)[i],i);
        }
        return campusMap.get(campus);
    }

}
