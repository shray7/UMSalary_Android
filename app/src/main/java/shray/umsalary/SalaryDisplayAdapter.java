package shray.umsalary;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by shray on 3/8/15.
 */
public class SalaryDisplayAdapter extends ArrayAdapter<Salary> {
    public SalaryDisplayAdapter(Context context, ArrayList<Salary> list) {
        super(context, 0, list);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Salary salary = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.salarylist, parent, false);
        }
        // Lookup view for data population
        TextView name = (TextView) convertView.findViewById(R.id.name);
        TextView title = (TextView) convertView.findViewById(R.id.title);
        TextView department = (TextView) convertView.findViewById(R.id.department);
        TextView ftr = (TextView) convertView.findViewById(R.id.ftr);
        TextView gf = (TextView) convertView.findViewById(R.id.gf);
        // Populate the data into the template view using the data object

        name.setText(salary.Name);
        title.setText(salary.Title);
        department.setText(salary.Department);
        ftr.setText(salary.FTR);
        gf.setText(salary.GF);
        // Return the completed view to render on screen
        if (position % 2 == 1) {
            convertView.setBackgroundColor(Color.argb(200,2,47,96));
        } else {
            convertView.setBackgroundColor(Color.argb(245,191,155,37));
            TextView departmentLabel = (TextView) convertView.findViewById(R.id.departmentLabel);
            departmentLabel.setTextColor(Color.WHITE);
        }
        return convertView;
    }
}
