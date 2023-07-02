package com.example.cardiac_monitor;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * The RecordsActivity class is responsible for managing the activity that displays records from a database.
 */
public class RecordsActivity extends AppCompatActivity {

    MyDatabaseHelper myDatabaseHelper;
    SimpleCursorAdapter simpleCursorAdapter;

    ListView listView;
    TextView no_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);

       //Enable the back button to navigate up within the application.
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Display the icon in the action bar.
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //// Initialize the database helper class for managing the SQLite database.
        myDatabaseHelper = new MyDatabaseHelper(RecordsActivity.this);
        // Get a writable database.
        SQLiteDatabase sqLiteDatabase =  myDatabaseHelper.getWritableDatabase();

        listView = findViewById(R.id.list_view);
        no_text = findViewById(R.id.no_text);

        // Load data from the database and populate the list view.
        loadData();

        //handles item clicks
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                //a Cursor is obtained from the simpleCursorAdapter using the clicked item position i.
                Cursor cursor = (Cursor) simpleCursorAdapter.getItem(i);
                //get first column of the Cursor in the id.
                String _id = cursor.getString(0);


                AlertDialog.Builder builder = new AlertDialog.Builder(RecordsActivity.this);

                View view2 = getLayoutInflater().inflate(R.layout.action_popup_dialog, null);

                Button update, delete;

                update = view2.findViewById(R.id.update);
                delete = view2.findViewById(R.id.delete);

                //The custom view view2 is set as the view for the AlertDialog.Builder.
                builder.setView(view2);
                AlertDialog alertDialog = builder.create();
                // prevent the dialog from being dismissed when touched outside of it.
                alertDialog.setCanceledOnTouchOutside(false);

                update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        AlertDialog.Builder builder = new AlertDialog.Builder(RecordsActivity.this);

                        View view2 = getLayoutInflater().inflate(R.layout.popup_dialog, null);

                        EditText sys = view2.findViewById(R.id.systolic);
                        EditText dias = view2.findViewById(R.id.diastolic);
                        EditText date = (EditText) view2.findViewById(R.id.date);
                        EditText time = (EditText) view2.findViewById(R.id.time);
                        EditText comment = (EditText) view2.findViewById(R.id.comments);
                        EditText pulse = (EditText) view2.findViewById(R.id.pulse_rate);

                        Button yes = (Button) view2.findViewById(R.id.yes_btn);
                        Button no = (Button) view2.findViewById(R.id.no_btn);

                        Calendar calendar = Calendar.getInstance();

                        Date currentDate = calendar.getTime();
                        String date_v = DateFormat.getDateInstance(DateFormat.FULL).format(currentDate);
                        date.setText(date_v);

                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm a");
                        String time_v = simpleDateFormat.format(calendar.getTime());
                        time.setText(time_v);

                        builder.setView(view2);

                        AlertDialog alertDialog1 = builder.create();
                        alertDialog1.setCanceledOnTouchOutside(false);

                        yes.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                String sys_v = sys.getText().toString();
                                String dias_v = dias.getText().toString();
                                String comments_v = comment.getText().toString();
                                String pulse_v = pulse.getText().toString();

                                String pulse_status = "", pressure_status = "";

                                sys.setText("");
                                dias.setText("");
                                pulse.setText("");
                                date.setText("");
                                time.setText("");
                                comment.setText("");

                                if (TextUtils.isEmpty(sys_v)) {
                                    sys.setError("Required");
                                    return;
                                } else if (TextUtils.isEmpty(dias_v)) {
                                    dias.setError("Required");
                                    return;
                                } else if (TextUtils.isEmpty(comments_v)) {
                                    comment.setError("Required");
                                    return;
                                } else if (TextUtils.isEmpty(pulse_v)) {
                                    pulse.setError("Required");
                                    return;
                                }

                                if (Integer.parseInt(pulse_v) >= 60 && Integer.parseInt(pulse_v) <= 80) {
                                    pulse_status += "normal";
                                } else {
                                    pulse_status += "exceptional";
                                }

                                int x = Integer.parseInt(sys_v);
                                int y = Integer.parseInt(dias_v);

                                if ((x >= 90 && x <= 140) && (y >= 60 && y <= 90)) {
                                    pressure_status += "normal";
                                } else if (x > 140 || y > 90) {
                                    pressure_status += "high";
                                } else if (x < 90 || y < 60) {
                                    pressure_status += "low";
                                }

                                //update a record in the database
                                boolean id = myDatabaseHelper.updateData(_id, sys_v, dias_v, pressure_status, pulse_v, pulse_status, date_v, time_v, comments_v);


                                if (id) {
                                    Toast.makeText(RecordsActivity.this, "data is updated", Toast.LENGTH_SHORT).show();

                                } else {
                                    Toast.makeText(RecordsActivity.this, "data is not updated", Toast.LENGTH_SHORT).show();
                                }

                                loadData();

                                alertDialog.dismiss();
                                alertDialog1.dismiss();


                            }
                        });

                        no.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                //Toast.makeText(getActivity(),"no",Toast.LENGTH_SHORT).show();
                                alertDialog1.dismiss();
                            }
                        });

                        alertDialog1.show();

                    }
                });

                delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        long f = myDatabaseHelper.deleteData(_id);
                        if(f>0)
                        {
                            Toast.makeText(RecordsActivity.this,"Data is deleted",Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(RecordsActivity.this,"Data is not deleted",Toast.LENGTH_SHORT).show();

                        }

                        alertDialog.dismiss();
                        loadData();
                    }
                });

                alertDialog.show();

            }
        });



    }

    /**
     * Load data from the database and populate the list view.
     */
    public void loadData()
    {
        //obtain a SimpleCursorAdapter for populating the listView with data from the database.
        simpleCursorAdapter = myDatabaseHelper.populateListViewFromDB();
        listView.setAdapter(simpleCursorAdapter);


        //If the listView contains less than 1 item (no records), the "No Records" message is displayed in the no_text view, and the listView is hidden (setVisibility(View.GONE)).
        if(listView.getCount()<1)
        {
            no_text.setText("No Records");
            no_text.setVisibility(View.VISIBLE);
            listView.setVisibility(View.GONE);
        }
        //If the listView contains at least 1 item, the "No Records" message is hidden, and the listView is displayed (setVisibility(View.VISIBLE)).
        else
        {
            no_text.setVisibility(View.GONE);
            listView.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == android.R.id.home)
        {
            this.finish();
        }

        return super.onOptionsItemSelected(item);
    }
}