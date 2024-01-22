package com.example.salemalmosadderd308.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;


import com.example.salemalmosadderd308.Database.Repository;
import com.example.salemalmosadderd308.R;
import com.example.salemalmosadderd308.entities.Excursion;
import com.example.salemalmosadderd308.entities.Vacation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ExcursionDetails extends AppCompatActivity {
    EditText excursionTitleText;
    EditText excursionDateText;
    //EditText excursionAlertText;

    int excursionId;
    int vacationId;
    String title;
    String date;

    String vacStartDate;
    String vacEndDate;

    //String alertDateObject;

    final Calendar myCalendar = Calendar.getInstance();

    DatePickerDialog.OnDateSetListener excursionDateCalendar;
    //DatePickerDialog.OnDateSetListener alertDateCalendar;


    Repository eRepository;

    //int numExc;
    Excursion currentExc;
    //Vacation currentVac;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excursion_details);

        eRepository = new Repository(getApplication());
        excursionTitleText = findViewById(R.id.excursion_title);
        excursionDateText = findViewById(R.id.excursion_date);
        //excursionAlertText = findViewById(R.id.excursion_alert_date);

        vacationId = getIntent().getIntExtra("vacationId", -1);
        excursionId = getIntent().getIntExtra("excursionId", -1);
        title = getIntent().getStringExtra("excursionTitle");
        date = getIntent().getStringExtra("excursionStartDate");

        //how to get data from another class object, create object list, modified for loop,
        //matching variables from class to class, declare variable then set.
        List<Vacation> myVacations = eRepository.getAllVacations();
        for (Vacation v : myVacations){
            if(v.getVacationId() == vacationId){
                vacStartDate = v.getVacationStartDate();
                vacEndDate = v.getVacationEndDate();
                break;
            }
        }

        /*
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        excursionAlertText.setText(sdf.format(new Date()));

         */

        if (title != null) {
            excursionTitleText.setText(title);
            excursionDateText.setText(date);
        }

        excursionDateText = findViewById(R.id.excursion_date);
        excursionDateCalendar = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String myFormat = "MM/dd/yy";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                updateLabelDate();
            }

        };

        excursionDateText.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(ExcursionDetails.this, excursionDateCalendar, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });


        /*
        alertDateCalendar = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub

                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                updateAlert();
            }

        };

        excursionAlertText.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Date date;
                //get value from other screen,but I'm going to hard code it right now
                String info = excursionAlertText.getText().toString();
                if (info.equals("")) info = "02/10/22";
                try {
                    myCalendar.setTime(sdf.parse(info));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                new DatePickerDialog(ExcursionDetails.this, alertDateCalendar, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

         */

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_excursion_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if(id == android.R.id.home){
            this.finish();
            return true;
        } //overriding the back button goes straight to onResume

        if (id == R.id.excursion_save) {

            date = excursionDateText.getText().toString();
            //vacStartDate = currentVac.getVacationStartDate();
            //vacEndDate = currentVac.getVacationEndDate();

            if (dateCheckExcursion() && dateValidation(date)) {

                if (excursionId == -1) {

                    String title = excursionTitleText.getText().toString();
                    //String date = excursionDateText.getText().toString();

                    Excursion newExcursion = new Excursion(0, vacationId, title, date);

                    eRepository.insert(newExcursion);

                    Toast.makeText(ExcursionDetails.this, "Excursion Added", Toast.LENGTH_LONG).show();

                    this.finish();

                } else if (excursionId >= 0) {

                    String title = excursionTitleText.getText().toString();
                    String date = excursionDateText.getText().toString();

                    Excursion newExcursion = new Excursion(excursionId, vacationId, title, date);

                    eRepository.update(newExcursion);

                    Toast.makeText(ExcursionDetails.this, "Excursion Updated", Toast.LENGTH_LONG).show();

                    this.finish();
                }


            } else {
                Toast.makeText(ExcursionDetails.this, "Please enter a date within the vacation date range", Toast.LENGTH_LONG).show();
            }

            return true;
        }


        if (id == R.id.excursion_delete) {

            for (Excursion exc : eRepository.getAllExcursions()) {
                if (exc.getExcursionId() == excursionId) currentExc = exc;
            }

            //Integer.parseInt(String.valueOf(vacationId)))
            //if()
            eRepository.delete(currentExc);
            Toast.makeText(ExcursionDetails.this, currentExc.getExcursionTitle() + " was deleted", Toast.LENGTH_LONG).show();
            this.finish();
        }

        if(id == R.id.excursion_alert){
            String title = excursionTitleText.getText().toString();
            String dateFromScreen = excursionDateText.getText().toString();
            String myFormat = "MM/dd/yy"; //In which you need put here
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
            Date myDate = null;
            try {
                myDate = sdf.parse(dateFromScreen);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            //String titleS = excursionTitleText.getText().toString();

            try{
                Long trigger = myDate.getTime();
                Intent intent = new Intent(ExcursionDetails.this, MyReceiver.class);
                intent.putExtra("key", title);
                PendingIntent sender = PendingIntent.getBroadcast(ExcursionDetails.this, ++MainActivity.numAlert, intent, PendingIntent.FLAG_IMMUTABLE);
                AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC_WAKEUP, trigger, sender);}
            catch (Exception e){

            }
            return true;
        }

        return super.onOptionsItemSelected(item);

    }

    public boolean dateCheckExcursion(){

        Date excursionStartDate = new Date();
        try{
            excursionStartDate = new SimpleDateFormat("MM/dd/yy").parse(date);
        }catch (ParseException e){
            e.printStackTrace();
        }

        Date startDateVac = new Date();
        try {
            startDateVac = new SimpleDateFormat("MM/dd/yy").parse(vacStartDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Date endDateVac = new Date();
        try {
            endDateVac = new SimpleDateFormat("MM/dd/yy").parse(vacEndDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if(startDateVac.after(excursionStartDate) || endDateVac.before(excursionStartDate)){
            return false;
        }else{
            return true;
        }
    }

    public boolean dateValidation(String date){

        if(date.trim().equals("")){
            return true;
        }else{
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy");
            sdf.setLenient(false);
            try{
                Date validDate = sdf.parse(date);
                //date is valid

            }catch (ParseException e){
                e.printStackTrace();
                Toast.makeText(ExcursionDetails.this, "Please enter a valid date.", Toast.LENGTH_LONG).show();
                return false;
            }

            return true;

        }

    }
/*
    private void updateAlert() {
        String myFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat,Locale.US);

        excursionAlertText.setText(sdf.format(myCalendar.getTime()));
    }

 */

    private void updateLabelDate() {
        String myFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat,Locale.US);

        excursionDateText.setText(sdf.format(myCalendar.getTime()));
    }

}