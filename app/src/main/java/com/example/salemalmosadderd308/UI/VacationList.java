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

    private Repository mRepository;
    int vacationId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacation_list);

        RecyclerView recyclerView = findViewById(R.id.vacation_recycler_view);
        final VacationAdapter vacationAdapter = new VacationAdapter(this);

        recyclerView.setAdapter(vacationAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mRepository = new Repository(getApplication());
        List<Vacation> allVacations = mRepository.getAllVacations();


        FloatingActionButton fab = findViewById(R.id.vacationListfab);

        vacationAdapter.setVacations(allVacations);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VacationList.this, VacationDetails.class);
                startActivity(intent);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_vacation_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

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
        vacationAdapter.setVacations(mRepository.getAllVacations());

    }

}