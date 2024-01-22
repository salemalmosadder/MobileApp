package com.example.salemalmosadderd308.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.salemalmosadderd308.Database.Repository;
import com.example.salemalmosadderd308.entities.Vacation;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import com.example.salemalmosadderd308.R;

import java.util.List;

public class VacationList extends AppCompatActivity {

    private Repository mRepository; //will get items from the repository
    int vacationId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacation_list);

        RecyclerView recyclerView = findViewById(R.id.vacation_recycler_view);
        final VacationAdapter vacationAdapter = new VacationAdapter(this);
        //need to make an instance of the adapter

        recyclerView.setAdapter(vacationAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mRepository = new Repository(getApplication());
        List<Vacation> allVacations = mRepository.getAllVacations();

        //populateData(); //populate data

        FloatingActionButton fab = findViewById(R.id.vacationListfab);

        vacationAdapter.setVacations(allVacations);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VacationList.this, VacationDetails.class);
                //intent.putExtra("vacTitle","my title");
                startActivity(intent);
            }
        });


    }


    /*public void populateData() {
        //Repository repository1 = new Repository(getApplication());
        Vacation vacation1 = new Vacation(1, "France", "Paris Hotel", "6/4/23","7/1/23");
        mRepository.insert(vacation1);
        Vacation vacation2 = new Vacation(2, "Japan","Tokyo Hotel", "9/4/23", "10/01/23");
        mRepository.insert(vacation2);
    }

     */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_vacation_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.vacation_refresh) {
            RecyclerView recyclerView = findViewById(R.id.vacation_recycler_view);
            final VacationAdapter adapter = new VacationAdapter(this);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            adapter.setVacations(mRepository.getAllVacations());

            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onResume() {
        super.onResume();
        RecyclerView recyclerView = findViewById(R.id.vacation_recycler_view);
        final VacationAdapter vacationAdapter = new VacationAdapter(this);
        recyclerView.setAdapter(vacationAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //List<Vacation> filteredVacations = new ArrayList<>();
        //for (Vacation v : mRepository.getAllVacations()) {
        //if(v.getVacationId() == vacationId) filteredVacations.add(v);
        //}
        vacationAdapter.setVacations(mRepository.getAllVacations());

        //Toast.makeText(VacationList.this,"refresh list",Toast.LENGTH_LONG).show();
    }

}