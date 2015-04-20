package shray.umsalary;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.view.Gravity;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by shray on 3/7/15.
 */
public class DownloadContent extends AsyncTask<String, String, String> {
    private ListView lv;
    private Context context;
    private ProgressDialog mpd;

    public DownloadContent(Context context, ListView dataField, ProgressDialog pd) {
        this.context = context;
        this.lv = dataField;
        this.mpd = pd;
    }


    protected void onPreExecute() {

    }

    @Override
    protected String doInBackground(String... arg0) {
        try {
            String link = (String) arg0[0];
            URL url = new URL(link);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.connect();
            InputStream is = conn.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader
                    (is, "UTF-8"));
            String data = null;
            String webPage = "";
            while ((data = reader.readLine()) != null) {
                webPage += data + "\n";
            }
            return webPage;
        } catch (Exception e) {
            return new String("Exception: " + e.getMessage());
        }
    }

    @Override
    protected void onPostExecute(String result) {
        mpd.hide();

        JSONArray o;
        ArrayList salaryList = new ArrayList();

        try {
            o = new JSONArray(result);
            for (int i = 0; i < o.length(); i++) {
                Salary salary = new Salary();
                salary.Name = o.getJSONObject(i).getString("Name");
                salary.Title = o.getJSONObject(i).getString("Title");
                salary.Department = o.getJSONObject(i).getString("Department");
                salary.FTR = o.getJSONObject(i).getString("FTR");
                salary.GF = o.getJSONObject(i).getString("GF");
                salaryList.add(salary);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        lv.setAdapter(new SalaryDisplayAdapter(this.context, salaryList));
        if (salaryList.size() == 0) {
            Toast toast = Toast.makeText(this.context, "No Results Found", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
    }
}

