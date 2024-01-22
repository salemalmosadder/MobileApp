package com.example.salemalmosadderd308.UI;

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

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import com.example.salemalmosadderd308.Database.Repository;
import com.example.salemalmosadderd308.R;
import com.example.salemalmosadderd308.entities.Excursion;
import com.example.salemalmosadderd308.entities.Vacation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

// B2 VacationDetails created to view and edit vacations
public class VacationDetails extends AppCompatActivity {
    EditText vacationIdText;
    EditText vacationTitleText;
    EditText vacationLodgingText;
    EditText vacationStartText;
    EditText vacationEndText;

    int vacationId;
    String title;
    String lodging;
    String start_date;
    String end_date;

    Vacation currentVac;
    int numExc;
    int excursionId;

    final Calendar myCalendarStart = Calendar.getInstance();
    final Calendar myCalendarEnd = Calendar.getInstance();

    DatePickerDialog.OnDateSetListener startDate;
    DatePickerDialog.OnDateSetListener endDate;

    DatePickerDialog.OnDateSetListener alertDate;

    private Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacation_details_excursion_list);

        repository = new Repository(getApplication());
        vacationTitleText = findViewById(R.id.vacation_title);
        vacationLodgingText = findViewById(R.id.vacation_lodging);
        vacationStartText = findViewById(R.id.startDate);
        vacationEndText = findViewById(R.id.endDate);

        vacationId = getIntent().getIntExtra("vacationId", -1);
        title = getIntent().getStringExtra("vacationTitle");
        lodging = getIntent().getStringExtra("vacationLodging");
        start_date = getIntent().getStringExtra("vacationStartDate");
        end_date = getIntent().getStringExtra("vacationEndDate");

        String myFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        if (title != null) {
            vacationId = getIntent().getIntExtra("vacationId", -1);
            String vacStringStart = getIntent().getStringExtra("vacStartDate");
            String vacStringEnd = getIntent().getStringExtra("vacEndDate");
            vacationTitleText.setText(title);
            vacationLodgingText.setText(lodging);
            vacationStartText.setText(start_date);
            vacationEndText.setText(end_date);
        }

        vacationStartText = findViewById(R.id.startDate);
        startDate = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendarStart.set(Calendar.YEAR, year);
                myCalendarStart.set(Calendar.MONTH, monthOfYear);
                myCalendarStart.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String myFormat = "MM/dd/yy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                updateLabelStart();
            }

        };

        vacationStartText.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(VacationDetails.this, startDate, myCalendarStart
                        .get(Calendar.YEAR), myCalendarStart.get(Calendar.MONTH),
                        myCalendarStart.get(Calendar.DAY_OF_MONTH)).show();

            }
        });

        vacationEndText = findViewById(R.id.endDate);
        endDate = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendarEnd.set(Calendar.YEAR, year);
                myCalendarEnd.set(Calendar.MONTH, monthOfYear);
                myCalendarEnd.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String myFormat = "MM/dd/yy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                updateLabelEnd();
            }

        };

        vacationEndText.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(VacationDetails.this, endDate, myCalendarEnd
                        .get(Calendar.YEAR), myCalendarEnd.get(Calendar.MONTH),
                        myCalendarEnd.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        /*
        alertDate = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub

                myCalendarStart.set(Calendar.YEAR, year);
                myCalendarStart.set(Calendar.MONTH, monthOfYear);
                myCalendarStart.set(Calendar.DAY_OF_MONTH, dayOfMonth);


                updateLabelAlert();
            }

        };

        alertInputText.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Date date; //date object, checking if empty and adding if empty
                String info = alertInputText.getText().toString();
                if (info.equals("")) info = "02/25/23";
                try {
                    myCalendarStart.setTime(sdf.parse(info));//<--- date object
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                new DatePickerDialog(VacationDetails.this, alertDate, myCalendarStart.get(Calendar.YEAR),
                        myCalendarStart.get(Calendar.MONTH), myCalendarStart.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

         */

        FloatingActionButton fab = findViewById(R.id.enter_excursion_details);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* if(vacationId==-1){
                    Vacation = new Vacation()
                }

                */
                Intent intent = new Intent(VacationDetails.this, ExcursionDetails.class);
                intent.putExtra("vacationId", vacationId);
                startActivity(intent);
            }
        });

        /* spinner display
        ArrayList<Excursion> excursionArrayList= new ArrayList<>();
        excursionArrayList.addAll(repository.getAllExcursions());
        ArrayList<String> excursionTitleList= new ArrayList<>();
        for(Excursion e : excursionArrayList){
            excursionTitleList.add(e.getExcursionTitle());
        }
        ArrayAdapter<String> excursionIdAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, excursionTitleList);
        Spinner spinner = findViewById(R.id.excursion_list);
        spinner.setAdapter(excursionIdAdapter);

         */

        startDate = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub

                myCalendarStart.set(Calendar.YEAR, year);
                myCalendarStart.set(Calendar.MONTH, monthOfYear);
                myCalendarStart.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                updateLabelStart();
            }

        };

        RecyclerView recyclerView = findViewById(R.id.excursion_recycler_view);
        repository = new Repository(getApplication());
        final ExcursionAdapter excursionAdapter = new ExcursionAdapter(this);
        recyclerView.setAdapter(excursionAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<Excursion> filteredExcursions = new ArrayList<>();
        for (Excursion e : repository.getAllExcursions()) {
            if (e.getVacationId() == vacationId) filteredExcursions.add(e);
        }
        excursionAdapter.setExcursions(filteredExcursions);

    }

    private void updateLabelStart() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        vacationStartText.setText(sdf.format(myCalendarStart.getTime()));
    }

    /*
    private void updateLabelAlert(){
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        alertInputText.setText(sdf.format(myCalendarStart.getTime()));
    }

     */

    private void updateLabelEnd() {
        String myFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        vacationEndText.setText(sdf.format(myCalendarEnd.getTime()));
    }

    private boolean dateCheck() {

        Date startDateVac = new Date();
        try {
            startDateVac = new SimpleDateFormat("MM/dd/yy").parse(start_date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Date endDateVac = new Date();
        try {
            endDateVac = new SimpleDateFormat("MM/dd/yy").parse(end_date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (startDateVac.before(endDateVac)){
            return true;
        }else{
            return false;
        }
    }

    public boolean dateValidationStart(String start_date){

        if(start_date.trim().equals("")){
            return true;
        }else{
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy");
            sdf.setLenient(false);
            try{
                Date validDate = sdf.parse(start_date);
                //date is valid

            }catch (ParseException e){
                e.printStackTrace();
                Toast.makeText(VacationDetails.this, "Please enter a valid date.", Toast.LENGTH_LONG).show();
                return false;
            }

            return true;

        }

    }

    public boolean dateValidationEnd(String end_date){

        if(end_date.trim().equals("")){
            return true;
        }else{
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy");
            sdf.setLenient(false);
            try{
                Date validDate = sdf.parse(end_date);
                //date is valid

            }catch (ParseException e){
                e.printStackTrace();
                Toast.makeText(VacationDetails.this, "Please enter a valid date.", Toast.LENGTH_LONG).show();
                return false;
            }

            return true;

        }

    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate(R.menu.menu_vacation_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item){
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.save) {

            start_date = vacationStartText.getText().toString();
            end_date = vacationEndText.getText().toString();

            if (dateCheck() && dateValidationStart(start_date) && dateValidationEnd(end_date)) {

                if (vacationId == -1) {

                    String title = vacationTitleText.getText().toString();
                    String lodging = vacationLodgingText.getText().toString();
                    //String start = vacationStartText.getText().toString();
                    //String end = vacationEndText.getText().toString();

                    Vacation newVacation = new Vacation(0, title, lodging, start_date, end_date);
                    //Database knows a value cannot be zero and auto generate is set, so set the ID to zero in the constructor

                    repository.insert(newVacation);

                    Toast.makeText(VacationDetails.this, "Vacation added successfully", Toast.LENGTH_LONG).show();

                    this.finish();
                } else {

                    String title = vacationTitleText.getText().toString();
                    String lodging = vacationLodgingText.getText().toString();
                    String start = vacationStartText.getText().toString();
                    String end = vacationEndText.getText().toString();

                    Vacation newVacation = new Vacation(vacationId, title, lodging, start, end);
                    //Database knows a value cannot be zero and auto generate is set, so set the ID to zero in the constructor

                    repository.update(newVacation);

                    Toast.makeText(VacationDetails.this, "Vacation added successfully", Toast.LENGTH_LONG).show();

                    this.finish();

                }

                return true;

            }else{
                Toast.makeText(VacationDetails.this, "Please make the end date after the start date", Toast.LENGTH_LONG).show();
                return false;
            }

        }

        if (id == R.id.delete) {
            for (Vacation vac : repository.getAllVacations()) {
                if (vac.getVacationId() == Integer.parseInt(String.valueOf(vacationId)))
                    currentVac = vac;
            }

            numExc = 0;
            for (Excursion exc : repository.getAllExcursions()) {
                if (exc.getVacationId() == Integer.parseInt(String.valueOf(vacationId)))
                    ++numExc;
            }

            if (numExc == 0) {
                repository.delete(currentVac);
                Toast.makeText(VacationDetails.this, currentVac.getVacationTitle() + " was deleted", Toast.LENGTH_LONG).show();
                this.finish();
            } else {
                Toast.makeText(VacationDetails.this, "Can't delete a vacation with excursions", Toast.LENGTH_LONG).show();
            }
            return true;
        }

        if (id == R.id.share) {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, vacationTitleText.getText().toString() + " " + vacationLodgingText.getText().toString() + " " +
                    vacationStartText.getText().toString() + " " + vacationEndText.getText().toString());
            sendIntent.putExtra(Intent.EXTRA_TITLE, "This destination looks amazing!");
            sendIntent.setType("text/plain");

            Intent shareIntent = Intent.createChooser(sendIntent, null);
            startActivity(shareIntent);
            return true;
        }

        if (id == R.id.start_alert)
        {
            String dateFromScreen = vacationStartText.getText().toString();
            String myFormat = "MM/dd/yy"; //In which you need put here
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
            Date myDate = null;
            try {
                myDate = sdf.parse(dateFromScreen);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            try{
                Long trigger = myDate.getTime();
                Intent intent = new Intent(VacationDetails.this, MyReceiver.class);
                intent.putExtra("key", title + " is starting");
                PendingIntent sender = PendingIntent.getBroadcast(VacationDetails.this, ++MainActivity.numAlert, intent, PendingIntent.FLAG_IMMUTABLE);
                AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC_WAKEUP, trigger, sender);}
            catch (Exception e){

            }
            return true;
        }

        //numAlert must have its own unique id and be static so the entire app and see

        if (id == R.id.end_alert) {
            String dateFromScreen = vacationStartText.getText().toString();
            String myFormat = "MM/dd/yy"; //In which you need put here
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
            Date myDate = null;
            try {
                myDate = sdf.parse(dateFromScreen);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            try{
                Long trigger = myDate.getTime();
                Intent intent = new Intent(VacationDetails.this, MyReceiver.class);
                intent.putExtra("key", title + " is ending");
                PendingIntent sender = PendingIntent.getBroadcast(VacationDetails.this, ++MainActivity.numAlert, intent, PendingIntent.FLAG_IMMUTABLE);
                AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC_WAKEUP, trigger, sender);}
            catch (Exception e){

            }
            return true;

        }

        if (id == R.id.refresh) {
            RecyclerView recyclerView = findViewById(R.id.excursion_recycler_view);
            repository = new Repository(getApplication());
            final ExcursionAdapter excursionAdapter = new ExcursionAdapter(this);
            recyclerView.setAdapter(excursionAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            List<Excursion> filteredExcursions = new ArrayList<>();
            for (Excursion e : repository.getAllExcursions()) {
                if (e.getVacationId() == vacationId) filteredExcursions.add(e);
            }
            excursionAdapter.setExcursions(filteredExcursions);

            return true;
        }

        return super.onOptionsItemSelected(item);

    }


    @Override
    protected void onResume() {
        super.onResume();
        RecyclerView recyclerView = findViewById(R.id.excursion_recycler_view);
        final ExcursionAdapter excursionAdapter = new ExcursionAdapter(this);
        recyclerView.setAdapter(excursionAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<Excursion> filteredExcursions = new ArrayList<>();
        for (Excursion e : repository.getAllExcursions()) {
            if(e.getVacationId() == vacationId) filteredExcursions.add(e);
        }
        excursionAdapter.setExcursions(filteredExcursions);
        //Toast.makeText(VacationDetails.this,"refresh details",Toast.LENGTH_LONG).show();
    }

}