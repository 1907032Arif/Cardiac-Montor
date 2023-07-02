package com.example.cardiac_monitor;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.cardiac_monitor.databinding.ActivityHomeBinding;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * The HomeActivity class represents the main activity of the Cardiac Monitor application.
 * It allows users to add records of blood pressure and pulse rate readings.
 */

public class  HomeActivity extends AppCompatActivity {


    //It is an auto-generated class that represents the layout file activity_home.xml.
    ActivityHomeBinding binding;
    //class to managing the SQLite database in the application.
    MyDatabaseHelper myDatabaseHelper;

    /**
     * Called when the activity is created. Initializes the activity and sets up the user interface.
     *
     * @param savedInstanceState The saved state of the activity, if available.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //inflate() method is used to create the user interface of the HomeActivity by inflating the XML layout file activity_home.xml
        //binding variable for inflating the activity_home.xml layout using the inflate() method.
        binding = ActivityHomeBinding.inflate(getLayoutInflater());

        // root view represents the top-level view of the inflated layout.
        View view = binding.getRoot();
        //enables back button to navigate up within the application.
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //display icon in the action bar.
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //sets content view of the activity to the view
        setContentView(view);
        //nitializes the database helper class for managing the SQLite database.
        myDatabaseHelper = new MyDatabaseHelper(HomeActivity.this);
        //Initialises a writable database
        SQLiteDatabase sqLiteDatabase =  myDatabaseHelper.getWritableDatabase();



        // set an empty text value to the status TextView
        binding.status.setText("");


        binding.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //construct an AlertDialog
                AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
                //inflates popup_dialog layout
                View view2 = getLayoutInflater().inflate(R.layout.popup_dialog,null);

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

                //sets the inflated view2 as the custom view for the AlertDialog.Builder
                builder.setView(view2);

                AlertDialog alertDialog = builder.create();
                alertDialog.setCanceledOnTouchOutside(false);

                yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String sys_v = sys.getText().toString();
                        String dias_v = dias.getText().toString();
                        String comments_v = comment.getText().toString();
                        String pulse_v = pulse.getText().toString();

                        String pulse_status="",pressure_status="";

                        sys.setText("");
                        dias.setText("");
                        pulse.setText("");
                        date.setText("");
                        time.setText("");
                        comment.setText("");

                        if(TextUtils.isEmpty(sys_v))
                        {
                            sys.setError("Required");
                            return;
                        }
                        else if(TextUtils.isEmpty(dias_v))
                        {
                            dias.setError("Required");
                            return;
                        }
                        else if(TextUtils.isEmpty(comments_v))
                        {
                            comment.setError("Required");
                            return;
                        }
                        else if(TextUtils.isEmpty(pulse_v))
                        {
                            pulse.setError("Required");
                            return;
                        }

                        if(Integer.parseInt(pulse_v)>=60 && Integer.parseInt(pulse_v)<=80)
                        {
                            pulse_status+= "normal";
                        }
                        else
                        {
                            pulse_status+= "exceptional";
                        }

                        int x = Integer.parseInt(sys_v);
                        int y = Integer.parseInt(dias_v);

                        if((x>=90 && x<=140) && (y>=60 && y<=90))
                        {
                            pressure_status+="normal";
                        }
                        else if(x>140 || y>90)
                        {
                            pressure_status+="high";
                        }
                        else if(x<90 || y<60)
                        {
                            pressure_status+="low";
                        }

                        //Inserts data into database.
                        long id =  myDatabaseHelper.insertData(sys_v,dias_v,pressure_status,pulse_v,pulse_status,date_v,time_v,comments_v);


                        if(id==-1)
                        {
                            Toast.makeText(HomeActivity.this,"data is not saved",Toast.LENGTH_SHORT).show();

                        }
                        else
                        {
                            Toast.makeText(HomeActivity.this,"data saved",Toast.LENGTH_SHORT).show();
                        }

                        alertDialog.dismiss();

                        int v = Integer.parseInt(pulse_v);

                        if(v>150)
                        {
                            v=150;
                        }


                        if(v>=60 && v<=80)
                        {
                            binding.status.setText("Normal");

                        }
                        else
                        {
                            binding.status.setText("Exceptional");
                        }


                    }
                });

                no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });

                alertDialog.show();


            }
        });

        binding.records.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this,RecordsActivity.class));
            }
        });


    }



    /**
     * Called when the activity is destroyed. Performs cleanup tasks.
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }

    /**
     * Handles the selection of options in the action bar.
     *
     * @param item The selected menu item.
     * @return True if the menu item is handled, false otherwise.
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == android.R.id.home)
        {
            this.finish();
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Handles the back button press. Displays a confirmation dialog before exiting the activity.
     */
    @Override
    public void onBackPressed() {
        new android.app.AlertDialog.Builder(this)
                .setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", (dialog, id) -> HomeActivity.super.onBackPressed())
                .setNegativeButton("No", null)
                .show();
    }
}