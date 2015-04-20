package shray.umsalary;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;


public class GetSalary extends ActionBarActivity {
    private TextView tv;
    private ListView lv;
    private ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_salary);
        Intent intent = getIntent();
        String firstName = intent.getStringExtra("fn");
        String lastName = intent.getStringExtra("ln");
        int year = intent.getIntExtra("year",0);
        int campus = intent.getIntExtra("campus",0);
        lv = (ListView) findViewById(R.id.salarylistview);

        String url = String.format("http://salaryapi.azurewebsites.net/api/salary/getsalary?firstName=%s&lastName=%s&year=%s&campus=%s"
        ,firstName, lastName,Integer.toString(year),Integer.toString(campus));

        pd = new ProgressDialog(this);
        pd.setMessage("Loading...");
        pd.show();

        new DownloadContent(this,lv,pd)
                .execute(url);




    }
    @Override
    public void onDestroy()
    {
        pd.dismiss();
     super.onDestroy();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_get_salary, menu);
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
