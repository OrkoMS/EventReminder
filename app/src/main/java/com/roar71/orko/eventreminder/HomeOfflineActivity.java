package com.roar71.orko.eventreminder;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HomeOfflineActivity extends AppCompatActivity implements View.OnClickListener {


    private int notificationId = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_offline);
        String getPlaceName = getIntent().getStringExtra("placeName");
        EditText placeName = findViewById(R.id.place_name);
        placeName.setText(getPlaceName);


    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View view) {

        FloatingActionButton fabList = findViewById(R.id.fabList);
        FloatingActionButton fabSave = findViewById(R.id.fabSave);


        fabSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText placeName = findViewById(R.id.place_name);
                EditText additionalNote = findViewById(R.id.note_text);

                Intent intent = new Intent(HomeOfflineActivity.this, AlarmReceiver.class);
                intent.putExtra("notificationId", notificationId);
                intent.putExtra("placeName", placeName.getText().toString());
                intent.putExtra("additionalNote", additionalNote.getText().toString());


                TimePicker time = findViewById(R.id.time_picker);
                DatePicker date = findViewById(R.id.date_picker);

                PendingIntent alarmIntent = PendingIntent.getBroadcast(HomeOfflineActivity.this, 0,
                        intent, PendingIntent.FLAG_CANCEL_CURRENT);
                AlarmManager alarm = (AlarmManager) getSystemService(ALARM_SERVICE);

                int hour = time.getCurrentHour();
                int minute = time.getCurrentMinute();
                Toast.makeText(HomeOfflineActivity.this, "Done!", Toast.LENGTH_SHORT).show();
                int day = date.getDayOfMonth();
                int month = date.getMonth();
                int year = date.getYear();

                // Create time.
                Calendar startTime = Calendar.getInstance();
                startTime.set(Calendar.HOUR_OF_DAY, hour);
                startTime.set(Calendar.MINUTE, minute);
                startTime.set(Calendar.SECOND, 0);
                startTime.set(Calendar.DAY_OF_MONTH, day);
                startTime.set(Calendar.MONTH, month);
                startTime.set(Calendar.YEAR, year);
                long alarmStartTime = startTime.getTimeInMillis();


                alarm.set(AlarmManager.RTC_WAKEUP, alarmStartTime, alarmIntent);

                Toast.makeText(HomeOfflineActivity.this, "Done!", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
