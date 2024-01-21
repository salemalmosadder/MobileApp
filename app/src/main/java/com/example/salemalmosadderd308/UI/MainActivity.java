package com.example.salemalmosadderd308.UI;

import static com.example.salemalmosadderd308.R.*;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.salemalmosadderd308.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);
        Button button=findViewById(id.button);
        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,VacationList.class);
                intent.putExtra("test", "Information sent");
                startActivity(intent);
            }
        });
    }
}