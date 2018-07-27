package ie.app.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import ie.app.R;
import ie.app.models.Donation;

public class Report extends Base {
    ListView listView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        listView = findViewById(R.id.reportList);
        final DonationAdapter adapter = new DonationAdapter(this, donations);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                long idCount = id+1;

               Toast.makeText(getApplicationContext(), "You selected row " + idCount + "for " +
                       "donation data " +  adapter.getString() + idCount, Toast.LENGTH_LONG).show();
            }
        });
    }

}

class DonationAdapter extends ArrayAdapter<Donation>
{
    private Context        context;
    public  List<Donation> donations;

    public DonationAdapter(Context context, List<Donation> donations)
    {
        super(context, R.layout.row_donate, donations);
        this.context   = context;
        this.donations = donations;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view       = inflater.inflate(R.layout.row_donate, parent, false);
        Donation donation   = donations.get(position);
        TextView amountView = view.findViewById(R.id.row_amount);
        TextView methodView = view.findViewById(R.id.row_method);

        amountView.setText("$" + donation.amount);
        methodView.setText(donation.method);



        return view;
    }

    @Override
    public int getCount()
    {
        return donations.size();
    }
    public String getString(){
        return donations.toString();
    }
}